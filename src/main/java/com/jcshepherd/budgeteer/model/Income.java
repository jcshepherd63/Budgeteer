package com.jcshepherd.budgeteer.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {

    private int incomeID;

    private String incomeName;

    private BigDecimal incomeValue;

    private LocalDate incomeDate;

    public Income(int incomeID, String incomeName, BigDecimal incomeValue, LocalDate incomeDate) {
        this.incomeID = incomeID;
        this.incomeName = incomeName;
        this.incomeValue = incomeValue;
        this.incomeDate = incomeDate;
    }

    public Income(){

    }

    public int getIncomeID() {
        return incomeID;
    }

    public void setIncomeID(int incomeID) {
        this.incomeID = incomeID;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public BigDecimal getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(BigDecimal incomeValue) {
        this.incomeValue = incomeValue;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }

    @Override
    public String toString() {
        return "Income { " +
                "Income ID = " + incomeID +
                "Income Name = " + incomeName +
                "Income Amount = " + incomeValue +
                "Income Date = " + incomeDate +
                "}";
    }
}
