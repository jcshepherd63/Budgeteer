import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import { createStore } from './store'
import axios from 'axios';
import router from './router';

const store = createStore();

createApp(App).use(router).use(store).mount('#app')

axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;
