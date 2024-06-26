<template>
  <div>
    <h1>Symbol Form</h1>
    <form @submit.prevent="submitForm">
      <label for="symbol">Symbol: </label>
      <input type="text" id="symbol" v-model="symbol" required /><br /><br />

      <input type="submit" value="Submit" @submit.prevent="onSubmit"/>
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
    async submitForm() {
      
      const url = `http://localhost:8081/api/dashboards/${this.symbol}`;

      try {
        const response = await fetch(url, {
          method: 'POST'
        });

        if (!response.ok) {
          throw new Error('Network response was not ok.');
        }

        // Reset the symbol input field
        this.symbol = '';
        window.location.reload();
      } catch (error) {
        // Handle error
        console.error('Error:', error);
      }
    }
  }
};
</script>