<template>
  <div>
    <h1>Symbol Form</h1>
    <form @submit.prevent="submitForm">
      <label for="symbol">Symbol:</label><br>
      <input type="text" id="symbol" v-model="symbol" required /><br /><br />

      <input type="submit" value="Submit" />
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      symbol: ''
    };
  },
  methods: {
    submitForm() {
      // Do something with the symbol value, for example, log it
      console.log('Symbol:', this.symbol);
    }
  }
};
</script>