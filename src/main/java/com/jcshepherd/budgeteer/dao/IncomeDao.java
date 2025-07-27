package com.jcshepherd.budgeteer.dao;

import com.jcshepherd.budgeteer.model.Income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeDao {

    Income getIncomeById (int incomeID);

    List<Income> getIncomes();

    Income getIncomeByIncomeName(String incomeName);

    Income getIncomeByAmount(BigDecimal incomeAmount);

    Income getIncomeByDate(LocalDate incomeDate);

    Income createIncome(Income income);

    Income updateIncomeByID (Income income, int incomeID);

    Income deleteIncome(int incomeID);
}
