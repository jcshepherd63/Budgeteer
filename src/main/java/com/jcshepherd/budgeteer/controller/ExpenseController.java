package com.jcshepherd.budgeteer.controller;

import com.jcshepherd.budgeteer.dao.ExpenseDao;
import com.jcshepherd.budgeteer.model.Expense;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseDao expenseDao;

    public ExpenseController(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseDao.getExpenses();
    }

    @GetMapping(path = "/{expense_id}")
    public Expense getExpense(@PathVariable int expense_id) {
        return expenseDao.getExpenseById(expense_id);
    }

    @GetMapping(path = "/expensename/{expense_name}")
    public Expense getExpenseByName(@PathVariable String expense_name){
        return expenseDao.getExpenseByExpenseName(expense_name);
    }

    @GetMapping(path = "/expensevalue/{expenseValue}")
    public Expense getExpenseByValue(@PathVariable BigDecimal expenseValue){
        return expenseDao.getExpenseByAmount(expenseValue);
    }

    @GetMapping(path = "/expensedate/{expenseDate}")
    public Expense getExpenseByDate(@PathVariable LocalDate expenseDate){
        return expenseDao.getExpenseByDate(expenseDate);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Expense addExpense(@RequestBody Expense newExpense) {
        return expenseDao.createExpense(newExpense);
    }

    @PutMapping(path= "/{expense_id}")
    public Expense updateExpense (@PathVariable int expense_id, @RequestBody Expense expense){
        return expenseDao.updateExpense(expense, expense_id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{expense_id}")
    public void deleteExpense (@PathVariable int expense_id){
        expenseDao.deleteExpense(expense_id);
    }


}
