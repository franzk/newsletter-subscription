<template>
    <h3>{{ action }} to this incredible newsletter !</h3>
  
    <label for="email">Your email : </label>
    <input 
        type="email" 
        id="email" 
        name="email" 
        v-model="email"
        required
    >&nbsp;
    <button @click="submit" :disabled="!isEmailValid">Subscribe</button>
    <p v-if="!isEmailValid && email.length > 0" class="error">
        Please enter a valid email address.
    </p>
    <p v-if="successMessage" class="success">{{ successMessage }}</p>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import axios, { AxiosError } from 'axios'

export default defineComponent({
    name: 'Subscription',

    props: {
        action: {
            type: String, // 'subscribe' | 'unsubscribe'
            default: 'subscribe'
        }
    },

    data() {
        return {
            email: '',
            successMessage: '',
            errorMessage: ''
        }
    },

    watch: {
        email() {
            this.initMessages()
        }
    },

    methods: {
        async submit() {
            this.initMessages()
            try {
                await axios.post(`${import.meta.env.VITE_API}/${this.action}`, { email: this.email })
                this.successMessage = `${this.action} ok`
            } catch (error: any) {               
                this.errorMessage = `${this.action} failed : ${(error as AxiosError).response?.data}`
                console.error(`${this.action} failed:`, error)
            }
        },

        initMessages() {
            this.successMessage = ''
            this.errorMessage = ''
        }
    },

    computed: {
        isEmailValid(): boolean {
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
            return !!this.email && emailPattern.test(this.email)
        }
    }
})
</script>

<style>
</style>