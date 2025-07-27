package com.jcshepherd.budgeteer.controller;

import com.jcshepherd.budgeteer.dao.IncomeDao;
import com.jcshepherd.budgeteer.model.Income;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeDao incomeDao;

    public IncomeController(IncomeDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeDao.getIncomes();
    }

    @GetMapping(path = "/{income_id}")
    public Income getIncome(@PathVariable int income_id) {
        return incomeDao.getIncomeById(income_id);
    }

    @GetMapping(path = "/incomename/{income_name}")
    public Income getIncomeByName(@PathVariable String income_name){
        return incomeDao.getIncomeByIncomeName(income_name);
    }

    @GetMapping(path = "/incomevalue/{incomeValue}")
    public Income getIncomeByValue(@PathVariable BigDecimal incomeValue){
        return incomeDao.getIncomeByAmount(incomeValue);
    }

    @GetMapping(path = "/incomedate/{incomeDate}")
    public Income getIncomeByDate(@PathVariable LocalDate incomeDate){
        return incomeDao.getIncomeByDate(incomeDate);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Income addIncome(@RequestBody Income newIncome) {
        return incomeDao.createIncome(newIncome);
    }

    @PutMapping(path= "/{income_id}")
    public Income updateIncome (@PathVariable int income_id, @RequestBody Income income){
        return incomeDao.updateIncomeByID(income, income_id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{income_id}")
    public void deleteIncome (@PathVariable int income_id){
        incomeDao.deleteIncome(income_id);
    }


}
