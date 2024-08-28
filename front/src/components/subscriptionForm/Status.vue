<template>
  <p :class="{ 'success': type === 'success', 'error': type === 'error' }">
    <slot />
  </p>
</template>

<script setup lang="ts">
/* props */
  const props = defineProps({
    type: {
      type: String as () => 'success' | 'error',
      default: 'success'
    }
  })
</script>

<style scoped>
p {
    margin: 0;
}

.error {
    color: #cf6679;
}

.success {
    color: #03dac6;
}
</style>