package com.jcshepherd.budgeteer.dao;

import com.jcshepherd.budgeteer.model.Expense;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseDao {

    Expense getExpenseById (int expenseID);

    List<Expense> getExpenses();

    Expense getExpenseByExpenseName(String expenseName);

    Expense getExpenseByAmount(BigDecimal expenseAmount);

    Expense getExpenseByDate(LocalDate expenseDate);

    Expense createExpense(Expense expense);

    Expense updateExpense(Expense expense, int expenseID);

    Expense deleteExpense(int expenseID);
}
