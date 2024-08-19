<template>
    <section class="subscription_page">
        <h3>{{ action }} to this incredible newsletter !</h3>
        <article class="subscription_form">
            <Captcha 
                v-if="recaptchaSiteKey"
                v-on:changeState="captchaChangeState($event)"
            />

           <InputEmail 
                :buttonEnabled="isEmailValid && captchaSuccess"
                :buttonText="action"
                v-on:emailChange="changeEmail($event)"
                v-on:emailSubmission="submit"
            />

            <Status 
                :message="status.message" 
                :type="status.type" 
            />
        </article>
    </section>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import axios, { AxiosError } from 'axios'
import Captcha from './Captcha.vue';
import InputEmail from './InputEmail.vue';
import Status from './Status.vue';

export default defineComponent({
    name: 'Subscription',

    components: {
        Captcha,
        InputEmail,
        Status
    },

    props: {
        action: {
            type: String, // 'Subscribe' | 'Unsubscribe'
            default: 'Subscribe'
        }
    },

    data() {
        return {
            email: '',
            status: {
                message: '',
                type: ''
            },
            captchaSuccess: false
        }
    },

    mounted() {
        this.captchaSuccess = !this.recaptchaSiteKey
    },

    watch: {
        email(val) {
            const emailFormatError = val && !this.isEmailValid 
            this.status.message = emailFormatError ? 'Please enter a valid email address.' : ''
            this.status.type =  emailFormatError ? 'error' : ''
        }
    },

    methods: {
        changeEmail(newEmail: string) {
            this.email = newEmail
        },

        captchaChangeState(val: boolean) {
            this.captchaSuccess = val
        },

        async submit() {
            this.status.message = ''
            try {
                const api_url = `${import.meta.env.VITE_API}/${this.action.toLocaleLowerCase()}`
                await axios.post(api_url, { email: this.email })
                this.status.message = `${this.action} ok`
                this.status.type = 'success'
            } catch (error: any) {               
                this.status.message = `${this.action} failed : ${(error as AxiosError).response?.data}`
                this.status.type = 'error'
            }
        }
    },

    computed: {
        isEmailValid(): boolean {
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
            return !!this.email && emailPattern.test(this.email)
        },
        recaptchaSiteKey(): string {
            return import.meta.env.VITE_RECAPTCHAV2_SITEKEY
        }
    }
})
</script>

<style scoped>
.subscription_page {
    max-width: 600px;
    margin: 50px auto;
    padding: 20px;
    background-color: #1e1e1e;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

 h3 {
    font-size: 24px;
    color: #ffffff; 
    margin-bottom: 20px;
    text-align: center;
}

.subscription_form {
    display: flex;
    flex-direction: column;
    gap: 10px;
    margin-top: 30px;
}
</style>