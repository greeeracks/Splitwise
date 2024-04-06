package com.saidul.Splitwise.service.strategy;

import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface SettleUpStrategy {
    List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses);
}
