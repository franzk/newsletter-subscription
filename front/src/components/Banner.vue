<template>
  <header class="banner">
    <h2>Newsletter subscription tool</h2>
    <nav>
      <ul>
        <li><router-link to="/">Subscribe</router-link></li>
        <li><router-link to="/mailing-list">Mailing list</router-link></li>
      </ul>
    </nav>
  </header>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Banner',
})
</script>

<style scoped lang="scss">
.banner {
  background-color: #333;
  padding: 1em;
  color: white;
  border-bottom: 1px solid white;
}

nav ul {
  list-style: none;
  display: flex;
  margin: 0;
}

nav ul li {
  flex: 1;
}

nav ul li a {
  color: white;
  text-decoration: none;
}

button {
  // padding: 10px 20px;
  font-size: 1em;
  color: #fff;
  background-color: #444;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #666;
}
</style>
