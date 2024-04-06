package com.saidul.Splitwise.service;

import com.saidul.Splitwise.Exception.InvalidExpenseIdException;
import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.Group;
import com.saidul.Splitwise.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Override
    public Expense addExpense(String description,double amount,Currency currency,int addedBy){
        Expense expense = new Expense();
        expense.setDescription(description);
        expense.setAmount(amount);
        expense.setCurrency(currency);
        expense.setAddedBy(userService.getUserById(addedBy));
        expense.setLocalDateTime(LocalDateTime.now());
        return expenseRepository.save(expense);
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        Expense savedExpense = expenseRepository.findExpenseById(expenseId);
        validExpense(savedExpense);
        return savedExpense;
    }

    @Override
    public Expense addExpenseToGroup(int expenseId, int groupId) {
        //validation
        Expense savedExpense = expenseRepository.findExpenseById(expenseId);
        validExpense(savedExpense);
        groupService.addExpenseToGroup(savedExpense, groupId);
        return savedExpense;
    }

    private void validExpense(Expense expense){
        if(expense == null){
            throw new InvalidExpenseIdException("Expense ID does not exist");
        }
    }
}
