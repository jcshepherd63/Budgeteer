<template>
    <div id = "recent-income">
        <h2>
            Recent Deposits
        </h2>
        <section id="income">
        <ul>
            <li v-for="income in incomes" :key="income.id"
                :class="{selected: income.id === $store.state.selectedIncome}">
                ${{ income.incomeValue }} // {{ income.incomeDate }} // {{ income.incomeName }}
            </li>
        </ul>
        </section>
    </div>
</template>

<script>
import { resourceService } from "../services/resourceService";

export default {
    computed : {
        incomes() {
            return this.$store.state.incomes;
        },
    },
    methods: {
        selected(incomeID) {
            this.$store.commit('setIncome', incomeID);
        },
    }
}

</script>

<style>

</style>