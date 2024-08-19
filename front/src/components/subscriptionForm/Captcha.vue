<template>
    <RecaptchaV2
        @widget-id="handleWidgetId"
        @error-callback="handleErrorCalback"
        @expired-callback="handleExpiredCallback"
        @load-callback="handleLoadCallback"
        theme="dark"
  />
</template>

<script lang="ts">
import { RecaptchaV2 } from "vue3-recaptcha-v2"
import { defineComponent } from "vue"

export default defineComponent({
    name: 'Captcha',

    components: {
        RecaptchaV2,
    },

    emits: ['changeState'],

    setup(_props, { emit }) {

        const handleWidgetId = (_widgetId: number) => {
        }

        const handleErrorCalback = () => {
            console.log("Error callback")
            emit('changeState', false)
        }

        const handleExpiredCallback = () => {
            console.log("Expired callback")  
            emit('changeState', false)      
        }

        const handleLoadCallback = (_response: unknown) => {
            emit('changeState', true)
        }

        return {
            handleWidgetId,
            handleErrorCalback,
            handleExpiredCallback,
            handleLoadCallback,
        }
    },
})
</script>

<style>

</style>
