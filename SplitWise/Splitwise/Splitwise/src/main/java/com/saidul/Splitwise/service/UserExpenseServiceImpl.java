package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.UserExpense;
import com.saidul.Splitwise.repository.UserExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExpenseServiceImpl implements UserExpenseService{
    @Autowired
    private UserExpenseRepo userExpenseRepo;
    @Override
    public UserExpense saveExpense(UserExpense userExpense) {
        return userExpenseRepo.save(userExpense);
    }
}
