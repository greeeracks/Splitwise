package com.saidul.Splitwise.entity;

import com.saidul.Splitwise.entity.constant.UserExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserExpense extends BaseModel{
    @ManyToOne
    private User user;
    private double amount;
    @Enumerated(EnumType.STRING)
    private UserExpenseType userExpenseType;
}
