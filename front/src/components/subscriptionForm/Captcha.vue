<template>
  <RecaptchaV2
    @widget-id="handleWidgetId"
    @error-callback="handleErrorCallback"
    @expired-callback="handleExpiredCallback"
    @load-callback="handleLoadCallback"
    theme="dark"
  />
</template>

<script setup lang="ts">
// imports
import { RecaptchaV2 } from 'vue3-recaptcha-v2'

// emits
const emit = defineEmits(['changeState'])

// methods
const handleWidgetId = (_widgetId: number) => {}

const handleErrorCallback = () => {
  console.log('Error callback')
  emit('changeState', false)
}

const handleExpiredCallback = () => {
  console.log('Expired callback')
  emit('changeState', false)
}

const handleLoadCallback = (_response: unknown) => {
  emit('changeState', true)
}
</script>
