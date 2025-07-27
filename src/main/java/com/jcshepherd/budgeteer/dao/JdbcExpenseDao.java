package com.jcshepherd.budgeteer.dao;

import com.jcshepherd.budgeteer.daoException.DaoException;
import com.jcshepherd.budgeteer.model.Expense;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExpenseDao implements ExpenseDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcExpenseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Expense getExpenseById(int expenseID) {
        Expense expense = null;
        String sql = "SELECT * FROM expense WHERE expense_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, expenseID);
            if (results.next()) {
                expense = mapRowToExpense(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return expense;
    }

    @Override
    public List<Expense> getExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expense ORDER BY expense_name";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Expense expense = mapRowToExpense(results);
                expenses.add(expense);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return expenses;
    }

    @Override
    public Expense getExpenseByExpenseName(String expenseName) {
        if (expenseName == null){
            expenseName = "";
        }
        Expense expense = null;
        String sql = "SELECT * FROM expense WHERE expense_name = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, expenseName);
            if (results.next()){
                expense = mapRowToExpense(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return expense;
    }

    @Override
    public Expense getExpenseByAmount(BigDecimal expenseAmount) {
        Expense expense = null;
        String sql = "SELECT * FROM expense WHERE expense_value = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, expenseAmount);
            if (results.next()){
                expense = mapRowToExpense(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return expense;
    }

    @Override
    public Expense getExpenseByDate(LocalDate expenseDate) {
        Expense expense = null;
        String sql = "SELECT * FROM expense WHERE expense_date = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, expenseDate);
            if (results.next()){
                expense = mapRowToExpense(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return expense;
    }

    @Override
    public Expense createExpense(Expense expense) {
        Expense createdExpense = null;

        String sql = "INSERT INTO expense (expense_name, expense_value, expense_date)" +
                "VALUES (?, ?, ?) RETURNING expense_id;";

        try {
            Integer expenseID = jdbcTemplate.queryForObject(sql, int.class, expense.getExpenseName(), expense.getExpenseValue(), expense.getDateOfExpense());
            createdExpense = getExpenseById(expenseID);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }

        return createdExpense;
    }

    @Override
    public Expense updateExpense(Expense expense, int expenseID) {
        Expense updatedExpense = null;
        String sql = "UPDATE expense SET expense_name = ?, expense_amount = ?, expense_date = ? WHERE expense_id = ?";

        try{
            int rowsAffected = jdbcTemplate.update(sql, expense.getExpenseName(), expense.getExpenseValue(), expense.getDateOfExpense(), expenseID);
            if (rowsAffected == 0){
                throw new DaoException("Zero rows affected, at least 1 expected");
            }
            updatedExpense = getExpenseById(expense.getExpenseID());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to the database", e);
        }
        return updatedExpense;
    }

    @Override
    public Expense deleteExpense(int expenseID) {
        Expense deletedExpense = null;
        jdbcTemplate.update("DELETE FROM expense WHERE expense_id = ?", expenseID);
        return deletedExpense;
    }

    private Expense mapRowToExpense(SqlRowSet results) {
        Expense expense = new Expense();
        expense.setExpenseID(results.getInt("expense_id"));
        expense.setExpenseName(results.getString("expense_name"));
        expense.setExpenseValue(results.getBigDecimal("expense_value"));
        expense.setDateOfExpense(results.getDate("expense_date").toLocalDate());

        return expense;
    }
}
