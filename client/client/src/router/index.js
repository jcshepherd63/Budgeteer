import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import CurrentGoals from '../views/CurrentGoals.vue';

const routes = [
    {
    path: '/',
    name: 'home',
    component: HomeView,
    },
    {
    path: '/login',
    name: 'login',
    component: LoginView,
    },
    {
    path: '/logout',
    name: 'logout',
    component: LogoutView
    },
    {
    path: '/currentGoals',
    name: 'currentGoals',
    component: CurrentGoals
    }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router