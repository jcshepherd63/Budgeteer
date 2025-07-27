package com.jcshepherd.budgeteer.dao;


import com.jcshepherd.budgeteer.daoException.DaoException;
import com.jcshepherd.budgeteer.model.Expense;
import com.jcshepherd.budgeteer.model.Income;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcIncomeDao implements  IncomeDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcIncomeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Income getIncomeById(int incomeID) {
        Income income = null;
        String sql = "SELECT * FROM income WHERE income_id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, incomeID);
            if (results.next()) {
                income = mapRowToIncome(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return income;
    }

    @Override
    public List<Income> getIncomes() {
        List<Income> incomes = new ArrayList<>();
        String sql = "SELECT * FROM income ORDER BY income_name";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Income income = mapRowToIncome(results);
                incomes.add(income);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return incomes;
    }

    @Override
    public Income getIncomeByIncomeName(String incomeName) {
        if (incomeName == null){
            incomeName = "";
        }
        Income income = null;
        String sql = "SELECT * FROM income WHERE income_name = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, incomeName);
            if (results.next()){
                income = mapRowToIncome(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }

        return income;
    }

    @Override
    public Income getIncomeByAmount(BigDecimal incomeAmount) {
        Income income = null;
        String sql = "SELECT * FROM income WHERE income_value = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, incomeAmount);
            if (results.next()){
                income = mapRowToIncome(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to the database", e);
        }
        return income;
    }

    @Override
    public Income getIncomeByDate(LocalDate incomeDate) {
        Income income = null;
        String sql = "SELECT * FROM income WHERE income_date = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, incomeDate);
            if (results.next()){
                income = mapRowToIncome(results);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return income;
    }

    @Override
    public Income createIncome(Income income) {
        Income createdIncome = null;

        String sql = "INSERT INTO income (income_name, income_value, income_date)" +
                "VALUES (?, ?, ?) RETURNING income_id;";

        try{
            Integer incomeID = jdbcTemplate.queryForObject(sql, int.class, income.getIncomeName(), income.getIncomeValue(), income.getIncomeDate());
            createdIncome = getIncomeById(incomeID);
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to database", e);
        }
        return createdIncome;
    }

    @Override
    public Income updateIncomeByID(Income income, int incomeID) {
        Income updatedIncome = null;
        String sql = "UPDATE income SET income_name = ?, income_value = ?, income_date = ? WHERE income_id = ?";

        try{
            int rowsAffected = jdbcTemplate.update(sql, income.getIncomeName(), income.getIncomeValue(), income.getIncomeDate(), incomeID);
            if (rowsAffected == 0){
                throw new DaoException("Zero rows affected, at least 1 expected");
            }
            updatedIncome = getIncomeById(income.getIncomeID());
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Can't connect to the database", e);
        }
        return updatedIncome;
    }

    @Override
    public Income deleteIncome(int incomeID) {
        Income deletedIncome = null;
        jdbcTemplate.update("DELETE FROM income WHERE income_id = ?", incomeID);
        return deletedIncome;
    }

    private Income mapRowToIncome(SqlRowSet results) {
        Income income = new Income();
        income.setIncomeID(results.getInt("income_id"));
        income.setIncomeName(results.getString("income_name"));
        income.setIncomeValue(results.getBigDecimal("income_value"));
        income.setIncomeDate(results.getDate("income_date").toLocalDate());

        return income;
    }
}
