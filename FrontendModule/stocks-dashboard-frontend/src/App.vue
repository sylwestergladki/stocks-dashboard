<template>
  <h2>STOCKS DASHBOARD APPLICATION</h2>
  <ChoiceForm/>
  <StockDataDisplay/>
</template>

<script>
import ChoiceForm from './components/ChoiceForm.vue';
import StockDataDisplay from './components/StockDataDisplay.vue';

export default {
  name: 'App',
  components: {
    ChoiceForm,
    StockDataDisplay
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
