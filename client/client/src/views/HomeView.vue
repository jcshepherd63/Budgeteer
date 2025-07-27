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

    export default{
        components: {RecentIncome, RecentExpenses, AddIncome, AddExpense}
    };

</script>

<style>
    #home{
        display: grid;
        grid-template-columns: auto;
        grid-template-rows: auto;
        grid-template-areas: 
            "recentIncome recentExpenses"
            "addIncome addExpense";
    }

    #recent-income{
        grid-area: recentIncome;
        padding-top: 100px;
    }

    #recent-expenses{
        grid-area: recentExpenses;
        padding-top: 100px;
    }

    #add-income{
        grid-area: addIncome;
    }

    #add-expense{
        grid-area: addExpense;
    }

</style>