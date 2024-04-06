package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupResponseDTO {
    private String groupName;
    private List<String> groupMembers;
    private double totalAmountSpent;
    private List<ExpenseResponseDTO> listOfExpense;
}
