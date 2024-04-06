package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExpenseResponseDTO {
    private String name;
    private double amount;
    private String type;
}
