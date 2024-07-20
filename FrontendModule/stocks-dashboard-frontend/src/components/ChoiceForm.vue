<template>
  <div class="choice-form">
    <h2>Choose your symbol</h2>
      <div class="form-container"> 
        <input type="text" v-model="searchQuery" placeholder="Enter stock symbol" autocomplete="off" class="symbol-input"/>
        <ul v-if="showSuggestions && results.length && symbol != null" class="suggestions">
          <li v-for="result in results" :key="result.symbol" @mousedown.prevent="selectSuggestion(result)">
            <div>{{ result.symbol }}</div>
            <div>{{ result.name }}</div>
          </li>
        </ul>
        <p v-else-if="searchQuery && symbol == null">No results found or your symbol is too small</p>
        <button @click="findTicker" class="find-button">Find</button>
        <p>Interval:</p>
        <select v-model="interval" class="interval-input">
          <option disabled value="">Select interval</option>
          <option value="day">Daily</option>
          <option value="month">Monthly</option>
          <option value="year">Yearly</option>
        </select>
        <p>Date from:</p>
        <input type="date" v-model="dateFrom" placeholder="Date from" class="date-input" />
        <p>Date to:</p>
        <input type="date" v-model="dateTo" placeholder="Date to" class="date-input" />
        
        <button @click="submitForm"  class="submit-button">Submit</button>
      </div>
  </div>
</template>

<script>


export default {
  data() {
    return {
      symbol: '',
      searchQuery: '',
      results: [],
      showSuggestions: false,
      interval: '',
      dateFrom: '',
      dateTo: ''
    };
  },
  methods: {
    async submitForm() {
      const url = `http://localhost:8081/api/dashboards/create-dashboard?symbol=${this.symbol}&interval=${this.interval}&dateFrom=${this.dateFrom}&dateTo=${this.dateTo}`;

      
      try {
        const response = await fetch(url, {
          method: 'POST'
        });
        const responseData = await response.json();
        console.log('Dashboard creation successful:', responseData);
        location.reload();
        // Handle success response if needed
      } catch (error) {
        console.error('Error creating dashboard:', error);
        // Handle error response
        alert(`Error creating dashboard: ${error.message}`);
      }
    },
    async findTicker() {
      try {
        this.showSuggestions = true;
        const response = await fetch(`http://localhost:8081/api/symbols/search/${this.searchQuery}`);
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        const data = await response.json();
        this.results = data.results.map(match => ({
          symbol: match['ticker'],
          name: match['name']
        }));
      } catch (error) {
        console.error("Error fetching search results:", error);
        this.results = [];
      }
    },
    
    selectSuggestion(result) {
      this.showSuggestions = false;
      this.symbol = result.symbol;
      this.searchQuery = result.symbol;
    },
    hideSuggestions() {
      setTimeout(() => {
        this.showSuggestions = false;
      }, 100);
    }
  }
};
</script>




<style>
.submit-button{
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
  margin-top: 1rem;
}

.submit-button:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.submit-button:active {
  background-color: #004494;
  transform: translateY(0);
}


input{
  margin-top: 1%;
}


.choice-form {
  background-color: rgb(76, 131, 122); 
  padding: 1.5rem;
  border-radius: 8px; 
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
  max-width: 400px;
  margin: 0 auto;
}

.choice-form h2 {
  color: #fff;
  font-size: 1.5rem; 
  margin-bottom: 1rem;
}

.choice-form input,
.choice-form button,
.interval-input {
  width: 100%;
  padding: 0.8rem 1rem;
  font-size: 1rem; 
  border: none;
  border-radius: 4px;
  box-sizing: border-box; 
}


.suggestions {
  list-style-type: none;
  padding: 0;
  margin: 0;
  border: 1px solid #ccc;
  background: white;
  max-height: 200px;
  overflow-y: auto;
  box-sizing: border-box;
  width: calc(100% - 2px); 
  font-size: 1rem;
  color: rgb(76, 131, 122);
  border-radius: 4px;
  text-align: right;
}

.suggestions li {
  padding: 8px;
  cursor: pointer;
}

.suggestions li:hover {
  background: #f0f0f0;
}

body {
  font-family: 'Roboto', sans-serif;
}

.suggestions li {
    padding: 8px;
    cursor: pointer;
    display: flex;
    justify-content: space-between; 
    border-bottom: 1px solid #f0f0f0; 
  } 

.form-container p{
  color: white;
  font-size: 20px;
}

.find-button{
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
  margin-top: 1rem;
}

.find-button:hover {
  background-color: #0056b3;
  transform: translateY(-2px);
}

.find-button:active {
  background-color: #004494;
  transform: translateY(0);
}

</style>