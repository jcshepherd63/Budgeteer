import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
    let store = _createStore({
        state: {
            incomes: [],
            expenses: [],
            goals: [],
            token: currentToken || '',
        },
        mutations: {
            setIncomes(state, income) {
                state.incomes = income;
            }
        }
    });
    return store;

}