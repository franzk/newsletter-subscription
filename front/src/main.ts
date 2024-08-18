import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import { install } from "vue3-recaptcha-v2"


const app = createApp(App)
app.use(router)

app.use(install, {
    sitekey: import.meta.env.VITE_RECAPTCHAV2_SITEKEY,
    cnDomains: false // default: false
})

app.mount('#app')