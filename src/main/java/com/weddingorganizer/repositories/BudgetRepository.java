package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weddingorganizer.models.BudgetItem;

public interface BudgetRepository extends JpaRepository<BudgetItem, Integer> {

}
