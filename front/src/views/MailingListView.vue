<template>
  <h3>List of Emails</h3>
  <ul>
    <li v-for="item in items" :key="item">
      <span class="email">{{ item }}</span>
      <span class="delete" @click="unsubscribe(item)">-</span>
    </li>
  </ul>
</template>

<script setup lang="ts">
/* imports */
import { ref, onMounted } from 'vue'
import axios from 'axios'
import subscription from '../service/subscription'

/* emails */
const items = ref([] as string[])

onMounted(async () => {
  getEmails()
})

const getEmails = async () => {
  try {
    const response = await axios.get(`${import.meta.env.VITE_API}/mailing-list`)
    items.value = response.data.map((item: { email: String }) => item.email)
  } catch (error: any) {
    console.error('get mailing-list failed:', error)
  }
}

/* unsubscribe */
const unsubscribe = async (email: string) => {
  if (confirm('Do you really want to remove this email ?')) {
    const result = await subscription('unsubscribe', email)
    if (result) {
      alert(`error : ${result}`)
    } else {
      alert(`The email ${email} has been successfully removed.`)
      getEmails()
    }
  }
}
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

li {
  background-color: #2c2c2c;
  border: 1px solid #333;
  padding: 8px 12px;
  margin-bottom: 8px;
  border-radius: 4px;
  font-size: 14px;
  color: #e0e0e0;
  transition:
    background-color 0.3s,
    box-shadow 0.3s;
  display: flex;
}

.email {
  flex: 1;
}

.delete {
  border: 1px solid white;
  padding: 2px 10px 2px 10px;
  border-radius: 50%;
  cursor: pointer;
}
</style>
