package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseToGroupMappingDTO {
    private int expenseId;
    private int groupId;
}
