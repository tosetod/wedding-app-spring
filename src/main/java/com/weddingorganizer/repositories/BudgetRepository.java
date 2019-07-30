package com.weddingorganizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weddingorganizer.models.BudgetItem;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetItem, Integer> {

}
