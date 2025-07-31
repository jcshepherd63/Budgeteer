<template>
    <div id = home>
        <recent-income/>
        <recent-expenses/>
        <add-expense/>
        <add-income/>
    </div>

</template>

<script>
    import RecentIncome from "../components/RecentIncome.vue"
    import RecentExpenses from "../components/RecentExpenses.vue";
    import AddExpense from "../components/AddExpense.vue"
    import AddIncome from "../components/AddIncome.vue"
    import { resourceService } from "@/services/resourceService";

    export default{
        components: {RecentIncome, RecentExpenses, AddIncome, AddExpense},
        created(){
            resourceService.getAllIncomes().then((response) => {
                this.$store.commit('setIncomes', response.data);
            });
        }
    };

</script>

<style>
    #home{
        display: grid;
        grid-template-columns: auto;
        grid-template-rows: 1fr 1fr;
        grid-template-areas: 
            "recentIncome recentExpenses"
            "addIncome addExpense";
    }

    #recent-income{
        grid-area: recentIncome;    
    }

    #recent-expenses{
        grid-area: recentExpenses;
        /* padding-top: 0px; */
    }

    #add-income{
        grid-area: addIncome;
    }

    #add-expense{
        grid-area: addExpense;
    }

</style>