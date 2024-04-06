package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.Expense;

import java.util.Currency;

public interface ExpenseService {
    Expense addExpense(String description, double amount, Currency currency, int addedBy);
    Expense getExpenseById(int expenseId);
    Expense addExpenseToGroup(int expenseId, int groupId);
}
