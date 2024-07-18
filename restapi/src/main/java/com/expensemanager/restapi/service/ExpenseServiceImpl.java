package com.expensemanager.restapi.service;

import com.expensemanager.restapi.entity.Expense;
import com.expensemanager.restapi.exception.ResourceNotFoundException;
import com.expensemanager.restapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenserepository;

    @Autowired
    private UserService userService;

    public Page<Expense> getAllExpenses(Pageable page)
    {
        return expenserepository.findByUserId(userService.getLoggedInUser().getId(),page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense=expenserepository.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
        if(expense.isPresent())
        {
            return expense.get();
        }
        throw new ResourceNotFoundException("Expense is not found for the id "+ id);


    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense=getExpenseById(id);
        expenserepository.delete(expense);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
        return expenserepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
       Expense existingexpense=getExpenseById(id);
       existingexpense.setName(expense.getName()!=null? expense.getName() : existingexpense.getName());
       existingexpense.setAmount(expense.getAmount()!=null? expense.getAmount() : existingexpense.getAmount());
       existingexpense.setDescription((expense.getDescription()!=null?expense.getDescription() : existingexpense.getDescription()));
       existingexpense.setCategory(expense.getCategory()!=null?expense.getCategory() : existingexpense.getCategory());
       existingexpense.setDate(expense.getDate()!=null?expense.getDate():existingexpense.getDate());
       return expenserepository.save(existingexpense);

    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenserepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category, page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenserepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
        if(startDate==null)
        {
            startDate=new Date(0);
        }
        if(endDate==null)
        {
            endDate=new Date(System.currentTimeMillis());
        }
        return expenserepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate, endDate, page).toList();
    }


}
