package com.saidul.Splitwise.repository;

import com.saidul.Splitwise.entity.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExpenseRepo extends JpaRepository<UserExpense , Integer> {
}
