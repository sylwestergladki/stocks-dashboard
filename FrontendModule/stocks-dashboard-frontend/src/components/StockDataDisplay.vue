<template>
    <div>
      <h2>Fetched Stock Data</h2>
      <div v-if="stockData">
        <p><strong>Symbol:</strong> {{ stockData.symbol }}</p>
        <!-- Display other properties of the fetched data -->
      </div>
      <div v-else>
        <p>No data available</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        stockData: null
      };
    },
    mounted() {
      // Call a method to fetch data when the component is mounted
      this.fetchStockData();
    },
    methods: {
      async fetchStockData() {
        const url = 'https://localhost:8081/stocks/{this.symbol}'; // Replace with your actual API endpoint
            try {
                const response = await fetch(url);
                    if (response.ok) {
                        this.stockData = await response.json();
                        console.log('Stock data fetched:', this.stockData);
                    } else {
                        console.error('Failed to fetch stock data:', response.statusText);
                    }   
            } catch (error) {
                console.error('There was an error fetching stock data:', error);
            }
        }
      }
    
  };
  </script>
  