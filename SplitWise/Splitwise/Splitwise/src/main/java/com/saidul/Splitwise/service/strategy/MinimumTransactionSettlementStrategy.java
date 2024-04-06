package com.saidul.Splitwise.service.strategy;

import com.saidul.Splitwise.dto.UserAmount;
import com.saidul.Splitwise.entity.Expense;
import com.saidul.Splitwise.entity.SettlementTransaction;
import com.saidul.Splitwise.entity.User;
import com.saidul.Splitwise.entity.UserExpense;
import com.saidul.Splitwise.entity.constant.UserExpenseType;
import com.saidul.Splitwise.repository.SettlementTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service

public class MinimumTransactionSettlementStrategy implements SettleUpStrategy{
    @Autowired
    private SettlementTransactionRepository settlementTransactionRepository;
    @Override
    public List<SettlementTransaction> getSettlementTransactions(List<Expense> expenses) {
        HashMap<User,Double> outstandingBalance = getOutstandingBalances(expenses);
        Comparator<UserAmount> maxHeapComparator = Comparator.comparingDouble(UserAmount::getAmount).reversed();
        Comparator<UserAmount> minHeapComparator = Comparator.comparingDouble(UserAmount::getAmount);
        PriorityQueue<UserAmount> maxHeap = new PriorityQueue<>(maxHeapComparator);
        PriorityQueue<UserAmount> minHeap = new PriorityQueue<>(minHeapComparator);

        for(Map.Entry<User,Double> entry : outstandingBalance.entrySet()){
            if(entry.getValue()<0){
                minHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }else if(entry.getValue() > 0){
                maxHeap.add(new UserAmount(entry.getKey(), entry.getValue()));
            }else{
                System.out.println("User doesn't need to participate in settlement.");
            }
        }

        List<SettlementTransaction> settlementTransactions = new ArrayList<>();
        while (!maxHeap.isEmpty() && !minHeap.isEmpty()){
            UserAmount borrower = minHeap.poll();
            UserAmount lender = maxHeap.poll();

            if(Math.abs(borrower.getAmount()) > lender.getAmount()){
                borrower.setAmount(borrower.getAmount() + lender.getAmount());
                minHeap.add(borrower);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                settlementTransactions.add(settlementTransaction);
                settlementTransactionRepository.save(settlementTransaction);
            } else if (Math.abs(borrower.getAmount()) < lender.getAmount()) {
                lender.setAmount(lender.getAmount() + borrower.getAmount());
                maxHeap.add(lender);
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), Math.abs(borrower.getAmount()));
                settlementTransactions.add(settlementTransaction);
                settlementTransactionRepository.save(settlementTransaction);
            }else {
                System.out.println(" Both Equal ");
                SettlementTransaction settlementTransaction = new SettlementTransaction(borrower.getUser(), lender.getUser(), lender.getAmount());
                settlementTransactions.add(settlementTransaction);
                settlementTransactionRepository.save(settlementTransaction);
            }
        }
        return settlementTransactions;
    }
    public HashMap<User, Double> getOutstandingBalances(List<Expense> expenses){
        HashMap<User, Double> outstandingBalanceMap = new HashMap<>();
        for(Expense expense : expenses){
            for(UserExpense userExpense : expense.getUserExpenses()){
                User participant = userExpense.getUser();
                double amount = userExpense.getAmount();
                if(outstandingBalanceMap.containsKey(participant)){
                    if(userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        outstandingBalanceMap.put(participant, outstandingBalanceMap.get(participant) + amount);
                    }else{
                        outstandingBalanceMap.put(participant, outstandingBalanceMap.get(participant) - amount);
                    }
                }else {
                    if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)){
                        outstandingBalanceMap.put(participant,0 + amount);
                    }else{
                        outstandingBalanceMap.put(participant,0 - amount);
                    }
                }
            }
        }
        return outstandingBalanceMap;
    }
}
