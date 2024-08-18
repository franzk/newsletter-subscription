<template>
    <p>
        <label for="email">Your email : </label>
        <input 
            type="email" 
            id="email" 
            name="email" 
            v-model="email"
            required
        >
        <button @click="submit" :disabled="!buttonEnabled">{{ buttonText }}</button>
    </p>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'InputEmail',

    emits: ['emailChange', 'emailSubmission'],

    props: {
        buttonEnabled: {
            type: Boolean,
            default: false
        },
        buttonText: {
            type: String,
            default: ''
        }
    },

    data() {
        return {
            email: ''
        }
    },

    watch: {
        email(val) {
            this.$emit('emailChange', val)
        }
    },

    methods: {
        async submit() {
            this.$emit('emailSubmission')
        }
    }
})
</script>

<style scoped>
p {
    margin: 0;
    display: flex;
    align-items: center;
    gap: 10px;
}

label {
    font-size: 16px;
    color: #ffffff; /* Texte en blanc */
    margin-right: 10px;
}

input {
    flex-grow: 1;
    padding: 10px;
    border: 1px solid #424242;
    border-radius: 5px;
    background-color: #333333;
    color: #e0e0e0;
}

input:focus {
    border-color: #bb86fc;
    outline: none;
    box-shadow: 0 0 5px #bb86fc;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    background-color: #bb86fc;
    color: #ffffff; /* Texte en blanc */
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:disabled {
    background-color: #424242;
    cursor: not-allowed;
}

button:not(:disabled):hover {
    background-color: #9a6efb;
}
</style>