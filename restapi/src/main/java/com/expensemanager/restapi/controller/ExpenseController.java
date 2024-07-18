package com.expensemanager.restapi.controller;

import com.expensemanager.restapi.entity.Expense;
import com.expensemanager.restapi.service.ExpenseService;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseservice;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable page)
    {
        return expenseservice.getAllExpenses(page).toList();
    }

    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id)
    {
        return expenseservice.getExpenseById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public void deleteExpenseById(@PathVariable Long id)
    {
     expenseservice.deleteExpenseById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense)
    {
        return expenseservice.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense,@PathVariable Long id)
    {
        return expenseservice.updateExpenseDetails(id,expense);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category,Pageable page)
    {
        return expenseservice.readByCategory(category,page);
    }

    @GetMapping("/expenses/name")
    public List<Expense> getExpenseByName(@RequestParam String keyword,Pageable page)
    {
        return expenseservice.readByName(keyword,page);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getExpenseByDates(@RequestParam(required = false) Date startDate,
                                           @RequestParam(required =false) Date endDate,
                                           Pageable page)
    {
        return expenseservice.readByDate(startDate, endDate, page);
    }
}
