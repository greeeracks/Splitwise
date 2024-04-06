package com.saidul.Splitwise.service;

import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.User;

public interface UserService {
    User signUp(String name, String email, String password);
    User login(String email, String password);
    boolean addFriend(int id, String email);
    User getUserById(int id);
}
