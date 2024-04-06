package com.saidul.Splitwise.mapper;

import com.saidul.Splitwise.dto.*;
import com.saidul.Splitwise.entity.*;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {
    public static UserLoginResponseDTO toDTO(User user){
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        List<UserFriendResponseDTO> friendList = new ArrayList<>();
        if(user.getFriends() != null){
            for(User friend : user.getFriends()){
                friendList.add(toFriendDTO(friend));
            }
            responseDTO.setFriendList(friendList);
        }

        List<GroupResponseDTO> groups = new ArrayList<>();
        if(user.getGroups() != null){
            for(Group group : user.getGroups()){
                groups.add(toGroupDTO(group));
            }
            responseDTO.setGroups(groups);
        }
        return responseDTO;
    }

    public static UserFriendResponseDTO toFriendDTO(User user){
        UserFriendResponseDTO responseDTO = new UserFriendResponseDTO();
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }
    public static GroupResponseDTO toGroupDTO(Group group){
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        groupResponseDTO.setGroupName(group.getName());
        groupResponseDTO.setGroupMembers(new ArrayList<>());
        for (User user : group.getMembers()){
            groupResponseDTO.getGroupMembers().add(user.getName());
        }
        groupResponseDTO.setListOfExpense(new ArrayList<>());
        for (Expense groupExpense : group.getExpenses()){
            groupResponseDTO.getListOfExpense().add(EntityDTOMapper.toExpenseDTO(groupExpense));
        }
        groupResponseDTO.setTotalAmountSpent(group.getTotalAmountSpent());
        return groupResponseDTO;
    }
    public static ExpenseResponseDTO toExpenseDTO(Expense expense){
        ExpenseResponseDTO expenseResponseDTO = new ExpenseResponseDTO();
        expenseResponseDTO.setDescription(expense.getDescription());
        expenseResponseDTO.setAmount(expense.getAmount());
        expenseResponseDTO.setCurrency(expense.getCurrency());
        expenseResponseDTO.setAddedBy(expense.getAddedBy().getName());
        expenseResponseDTO.setLocalDateTime(expense.getLocalDateTime());
        List<UserExpenseResponseDTO> members = new ArrayList<>();
        if(expense.getUserExpenses() != null) {
            for (UserExpense userExpense : expense.getUserExpenses()) {
                members.add(toUserExpenseDTO(userExpense));
            }
        }
        expenseResponseDTO.setMembers(members);
        return expenseResponseDTO;
    }
    public static UserExpenseResponseDTO toUserExpenseDTO(UserExpense userExpense){
        UserExpenseResponseDTO userExpenseResponseDTO = new UserExpenseResponseDTO();
        userExpenseResponseDTO.setName(userExpense.getUser().getName());
        userExpenseResponseDTO.setAmount(userExpense.getAmount());
        userExpenseResponseDTO.setType(userExpense.getUserExpenseType().toString());
        return userExpenseResponseDTO;
    }
    public static List<SettlementTransactionResponseDTO> toSettlementTransactionResponseDTOList(List<SettlementTransaction>
                                                                                                settlementTransactionList){
        List<SettlementTransactionResponseDTO> transactionResponseDTOS = new ArrayList<>();
        for (SettlementTransaction settlementTransaction : settlementTransactionList){
            SettlementTransactionResponseDTO transaction = new SettlementTransactionResponseDTO();
            transaction.setBorrower(settlementTransaction.getBorrower().getName());
            transaction.setLender(settlementTransaction.getLender().getName());
            transaction.setAmount(settlementTransaction.getAmount());
            transactionResponseDTOS.add(transaction);
        }
        return transactionResponseDTOS;
    }
}
