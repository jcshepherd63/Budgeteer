import axios from 'axios';

const resourceService = {
    getAllExpenses(){
        return axios.get('/expenses')
    },
    getAllIncomes(){
        return axios.get('/incomes')
    },
    getExpenseByID(){
        return axios.get(`/expenses/${expense_id}`)
    },
    getExpenseByName(){
        return axios.get(`/expenses/expensename/${expense_name}`)
    },
    // getExpenseByValue


}