package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.InvalidGroupIdException;
import com.saidul.Splitwise.entity.*;
import com.saidul.Splitwise.entity.constant.UserExpenseType;
import com.saidul.Splitwise.repository.GroupRepository;
import com.saidul.Splitwise.service.strategy.SettleUpStrategy;
import com.saidul.Splitwise.utilities.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserExpenseService userExpenseService;

    @Override
    public Group getGroupById(int groupId) {
        validateGroupId(groupId);
        return groupRepository.findGroupById(groupId);
    }

    @Override
    public Group createGroup(String groupName, Integer createdBy, List<Integer> members) {
        User admin = userService.getUserById(createdBy);
        Group group = new Group();
        group.setName(groupName);
        group.setAdmin(admin);
        List<User>groupMember = new ArrayList<>();
        groupMember.add(admin);
        if(admin.getGroups() == null){
            admin.setGroups(new ArrayList<>());
        }
        admin.getGroups().add(group);
        for(int memberId : members){
            User savedUser = userService.getUserById(memberId);
            groupMember.add(savedUser);
            if(savedUser.getGroups() == null){
                savedUser.setGroups(new ArrayList<>());
            }
            savedUser.getGroups().add(group);
        }
        group.setMembers(groupMember);
        return groupRepository.save(group);
    }

    @Override
    public Group addExpenseToGroup(Expense expense, int groupId) {
        validateGroupId(groupId);
        Group savedGroup = groupRepository.findGroupById(groupId);
        if(savedGroup.getExpenses() == null){
            savedGroup.setExpenses(new ArrayList<>());
        }
        savedGroup.getExpenses().add(expense);

        savedGroup.setTotalAmountSpent(savedGroup.getTotalAmountSpent() + CurrencyConverter.convertToINR(expense
                    .getCurrency(), expense.getAmount()));
        return groupRepository.save(savedGroup);
    }

    @Override
    public Group splitExpense(int groupId) {
        Group savedGroup = groupRepository.findGroupById(groupId);
        if(savedGroup.getExpenses() != null){
            for(Expense expense : savedGroup.getExpenses()){
                List<UserExpense> userExpenses = split(expense, savedGroup.getMembers());
                expense.setUserExpenses(userExpenses);
            }
        }
        return groupRepository.save(savedGroup);
    }
    private List<UserExpense> split(Expense expense, List<User> members){
        List<UserExpense> userExpenseList = new ArrayList<>();
        for(User user : members){
            UserExpense userExpense = new UserExpense();
            double expenseAmountInINR = CurrencyConverter.convertToINR(expense.getCurrency(), expense.getAmount());
            userExpense.setUser(user);
            userExpense.setAmount(expenseAmountInINR/(members.size()-1));
            userExpense.setUserExpenseType(UserExpenseType.HADTOPAY);
            if(expense.getAddedBy().equals(user)){
                userExpense.setAmount(expenseAmountInINR);
                userExpense.setUserExpenseType(UserExpenseType.PAID);
            }
            userExpenseList.add(userExpenseService.saveExpense(userExpense));
        }
        return userExpenseList;
    }

    private void validateGroupId(int groupId){
        if(groupRepository.findGroupById(groupId) == null){
            throw new InvalidGroupIdException("Given group ID does not exist");
        }
    }

    @Autowired
    private SettleUpStrategy settleUpStrategy;

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        validateGroupId(groupId);
        Group savedGroup = groupRepository.findGroupById(groupId);
        List<SettlementTransaction> transactions = settleUpStrategy.getSettlementTransactions(savedGroup.getExpenses());
        return transactions;
    }
}
