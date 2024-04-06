package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;
@Getter
@Setter
public class ExpenseResponseDTO {
    private String description;
    private Double amount;
    private Currency currency;
    private String addedBy;
    private LocalDateTime localDateTime;
    private List<UserExpenseResponseDTO> members;
}
