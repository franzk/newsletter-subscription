<template>
  <section class="subscription_page">
    <h3>Subscribe to this incredible newsletter !</h3>
    <article class="subscription_form">

      <Captcha 
        v-if="recaptchaSiteKey"
        @changeState="captchaChangeState($event)"
      />
      
      <InputEmail 
        v-if="!successMessage"
        v-model="email"
        :buttonEnabled="captchaSuccess"
        buttonText="Subscribe"
        @emailSubmission="submit"
        @validityChange="validityChange($event)"
      />
      <div v-else>
        <status type="success">{{ successMessage }}</status>
      </div>

      <Status v-if="errorMessage" type="error">{{ errorMessage }}</Status>
            
    </article>
  </section>
</template>

<script setup lang="ts">
/* imports */
  import { ref, onMounted, computed, watch } from 'vue'
  import subscription from '../service/subscription'
  import Captcha from '../components/subscriptionForm/Captcha.vue'
  import Status from '../components/subscriptionForm//Status.vue'
  import InputEmail from '../components/subscriptionForm//InputEmail.vue'
  
/* email */
  const email = ref('')

  watch(() => email.value, () => {
    successMessage.value = ''
  })

  const validityChange = (validity: boolean) => {
    errorMessage.value = validity ? '' : 'Please enter a valid email address !'
  } 

/* captcha */
  const captchaSuccess = ref(false)

  const captchaChangeState = (val: boolean) => { captchaSuccess.value = val }

  onMounted(() => {
    captchaSuccess.value = !recaptchaSiteKey
  })

  const recaptchaSiteKey = computed((): string => {
    return import.meta.env.VITE_RECAPTCHAV2_SITEKEY
  })

/* submit */
  const successMessage = ref('')
  const errorMessage = ref('')
  
  const submit = async () => {
    const result = await subscription('subscribe', email.value)
    if (result) {
      errorMessage.value = `Subscription failed : ${result}`
    } else {
      errorMessage.value = ''
      successMessage.value = 'Subscription ok'
    }
  }
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