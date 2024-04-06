package com.saidul.Splitwise.dto;

import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.entity.UserExpense;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.List;

@Getter
@Setter
public class AddExpenseRequestDTO {
    private String description;
    private Double amount;
    private Currency currency;
    private Integer addedBy;
}
