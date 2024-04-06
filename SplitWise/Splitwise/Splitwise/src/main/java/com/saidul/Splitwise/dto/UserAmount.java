package com.saidul.Splitwise.dto;

import com.saidul.Splitwise.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAmount {
    private User user;
    private double amount;

    public UserAmount(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }
}
