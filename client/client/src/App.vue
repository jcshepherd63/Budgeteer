<template>
  <header id = header >
    <h1>Budgeteer</h1>
    <nav id = budgeteerNav>
      <router-link :to = "{ name: 'home'}" class="nav-link"> 
        HOME
      </router-link>
      <router-link :to = "{ name: 'login'}" class="nav-link">
        LOGIN
      </router-link>
      <router-link :to = "{name: 'logout'}" class="nav-link">
        LOGOUT
      </router-link> 
      <router-link :to = "{name: 'currentGoals'}" class="nav-link">
        CURRENT GOALS
      </router-link>
    </nav>
  </header>
  <router-view/>
</template>

<script setup>

</script>

<style>
html {
  height: 100%;
  margin: 0;
  background-color: rgb(101, 196, 101);
}

header {
  position: fixed;
  top: 0%;
  left: 0%;
  background-color: green;
  width: 100%;
  overflow-x: hidden;
}

#header {
  height: 85px;
  width: 100vw;
  display: flex;
  align-items: center;
  padding: 0 20px;
  box-sizing: border-box;
  text-align: center;
  justify-content: space-around;
}

#budgeteerNav{
  display: flex;
  flex-direction: column;
  align-items: end;
}

.nav-link:hover {
  transform: scale(1.05);
  transition: transform 0.2s ease;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
