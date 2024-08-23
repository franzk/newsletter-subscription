<template>
  <div class="form">
    <label for="email">Your email : </label>
    <input 
      type="email" 
      id="email" 
      name="email" 
      v-model="email"
      required
    >
    <button @click="$emit('emailSubmission')" :disabled="!isButtonEnabled">
      {{ buttonText }}
    </button>  
  </div> 
  <div class="status">
    <Status v-if="email && !isEmailValid" type="error">
        Please enter a valid email address.
    </Status>
    <div v-else>&nbsp;</div>
  </div>
</template>

<script setup lang="ts">
/* imports */
  import { ref, watch, computed } from 'vue'
  import Status from './Status.vue'

/* props */
  const props = defineProps({
    modelValue: {
      type: String,
      default: ''
    },
    buttonEnabled: {
      type: Boolean,
      default: false
    },
    buttonText: {
      type: String,
      default: ''
    }
  })

/* modelValue */
  watch(() => props.modelValue, (newVal) => {
    email.value = newVal
  })

/* email */
  const email = ref(props.modelValue)
  const emailValidationPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/

  watch(() => email.value, (newVal) => {
    emit('update:modelValue', newVal)
  })

  const isEmailValid = computed(() => {
    return emailValidationPattern.test(email.value)
  })

/* button */
  const isButtonEnabled = computed(() => {
    return props.buttonEnabled && email.value && isEmailValid.value
  })

/* emits */
  const emit = defineEmits(['update:modelValue', 'emailSubmission'])
</script>

<style scoped>
.form {
  margin: 0;
  display: flex;
  align-items: center;
  gap: 10px;
}

label {
  font-size: 16px;
  color: #ffffff; 
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
  color: #ffffff; 
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

.status {
  text-align: center;
  font-size: 14px;
}
</style>