package com.expensemanager.restapi.repository;

import com.expensemanager.restapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //SELECT * FROM tbl_expenses WHERE category=?
    //Page<Expense> findByCategory(String category,Pageable page);
    Page<Expense> findByUserIdAndCategory(Long userId,String Category, Pageable page);
    //SELECT * FROM tbl_expenses WHERE name LIKE '%keyword%'
    //Page<Expense> findByNameContaining(String keyword,Pageable page);
    Page<Expense> findByUserIdAndNameContaining(Long userId,String keyword,Pageable page);
    //SELECT * FROM tbl_expenses WHERE date BETWEEN 'startDate' AND 'endDate'
    //Page<Expense> findByDateBetween(Date startDate, Date endDate, Pageable page);
    Page<Expense> findByUserIdAndDateBetween(Long userId,Date startDate, Date endDate, Pageable page);
    //SELECT * FROM tbl_expenses WHERE user_id=?
    Page<Expense> findByUserId(Long userId, Pageable page);

    //SELECT * FROM tbl_expenses WHERE user_id=? AND id=?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
