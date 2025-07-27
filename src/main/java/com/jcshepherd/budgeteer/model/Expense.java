package com.jcshepherd.budgeteer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {

    private int expenseID;

    private String expenseName;

    private BigDecimal expenseValue;

    private LocalDate dateOfExpense;

    public Expense(int expenseID, String expenseName, BigDecimal expenseValue, LocalDate dateOfExpense) {
        this.expenseID = expenseID;
        this.expenseName = expenseName;
        this.expenseValue = expenseValue;
        this.dateOfExpense = dateOfExpense;
    }

    public Expense(){

    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public BigDecimal getExpenseValue() {
        return expenseValue;
    }

    public void setExpenseValue(BigDecimal expenseValue) {
        this.expenseValue = expenseValue;
    }

    public LocalDate getDateOfExpense() {
        return dateOfExpense;
    }

    public void setDateOfExpense(LocalDate dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }

    @Override
    public String toString() {
        return "Expense {" +
                "Expense ID = " + expenseID +
                "Expense Name = " + expenseName +
                "Expense Amount = " + expenseValue +
                "Expense Date = " + dateOfExpense + "}";
    }
}
