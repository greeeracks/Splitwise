package com.saidul.Splitwise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity(name = "splitwise_group")
public class Group extends BaseModel{
    private String name;
    @ManyToOne
    private User admin;
    private double totalAmountSpent;
    private LocalDateTime creationDate;
    @OneToMany
    // admin becomes group member by default;
    private List<User> members;
    @OneToMany
    private List<Expense> expenses;
    @OneToMany
    private List<SettlementTransaction> settlementTransactions;
}
