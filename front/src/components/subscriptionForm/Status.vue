<template>
    <p v-if="!!message" :class="{ 'success': type === 'success', 'error': type === 'error' }">
        {{ message }}
    </p>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
    name: 'Status',

    props: {
        message: {
            type: String, 
            default: ''
        },
        type: {
            type: String, // 'success' | 'error'
            default: 'success'
        }
    }
})
</script>

<style scoped>
p {
    margin: 0;
    font-size: 14px;
}

.error {
    color: #cf6679;
}

.success {
    color: #03dac6;
}
</style>