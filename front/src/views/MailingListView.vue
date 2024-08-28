<template>
    <h3>List of Emails</h3>
    <ul>
       <li v-for="item in items" :key="item">{{ item }}</li> 
    </ul>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import axios from 'axios'

export default defineComponent({
    name: 'MailingList',

    data() {
        return {
            items: [] as string[]
        }
    },

   async  mounted() {
        this.getMailingList()
    },

    methods: {
        async getMailingList() {
            try {
                const response = await axios.get(`${import.meta.env.VITE_API}/mailing-list`)
                this.items = response.data.map((item : { email: String }) => item.email)
            } catch (error: any) {               
                console.error('get mailing-list failed:', error)
            }
        }
    }
})
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
  transition: background-color 0.3s, box-shadow 0.3s;
}

</style>