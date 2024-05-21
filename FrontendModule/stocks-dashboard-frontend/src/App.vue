<template>
  <h2>STOCKS DASHBOARD APPLICATION</h2>
  <ChoiceForm/>
</template>

<script>
import ChoiceForm from './components/ChoiceForm.vue'

export default {
  name: 'App',
  components: {
    ChoiceForm
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
