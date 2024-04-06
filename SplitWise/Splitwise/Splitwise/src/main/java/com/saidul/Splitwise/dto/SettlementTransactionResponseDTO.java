package com.saidul.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettlementTransactionResponseDTO {
    private String borrower;
    private String lender;
    private double amount;
}
