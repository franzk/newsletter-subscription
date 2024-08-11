<template>
    <p>{{ title }}</p>
  
    <label for="email">{{ label }}</label>
    <input 
        type="email" 
        id="email" 
        name="email" 
        v-model="email"
        required
    >&nbsp;
    <button @click="submit" :disabled="!isEmailValid">{{ buttonText }}</button>
    <p v-if="!isEmailValid && email.length > 0" style="color: red;">
        {{ emailInvalidErrorMessage }}
    </p>
    <p v-else>&nbsp;</p>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import axios from 'axios'

export default defineComponent({
    name: 'Unsubscribe',
    props: {
        title: {
            type: String,
            default: 'Why do you leave this incredible newsletter ?'
        },
        label: {
            type: String,
            default: 'Your email : '
        },
        buttonText: {
            type: String,
            default: 'Unsubscribe'
        },
        emailInvalidErrorMessage: {
            type: String,
            default: 'Please enter a valid email address.'
        }
    },
    data() {
        return {
            email: ''
        }
    },
    methods: {
        async submit() {
            try {
                await axios.post(`${import.meta.env.VITE_API}/unsubscribe`, { email: this.email });
                console.log('Unsubscription successful!');
            } catch (error) {
                console.error('Subscription failed:', error);
            }
        }
    },
    computed: {
        isEmailValid(): boolean {
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
            return !!this.email && emailPattern.test(this.email)
        }
    }
})
</script>

<style>
</style>