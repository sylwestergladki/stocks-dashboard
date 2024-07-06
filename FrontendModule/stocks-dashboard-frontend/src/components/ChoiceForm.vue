<template>
  <div class="choice-form">
    <h2>Choose your symbol</h2>
      <div class="symbol-container">
        <input type="text" v-model="searchQuery" @input="searchStocks" @focus="showSuggestions = true"
          @blur="hideSuggestions" placeholder="Enter stock symbol" autocomplete="off" class="symbol-input" />
        <ul v-if="showSuggestions && results.length" class="suggestions">
          <li v-for="result in results" :key="result.symbol" @mousedown.prevent="selectSuggestion(result)">
            <div>{{ result.symbol }}</div>
            <div>{{ result.name }}</div>
          </li>
        </ul>
        <p v-else-if="searchQuery">No results found or your symbol is too small</p>
      </div>
  </div>
</template>

<script>
import debounce from 'lodash/debounce';

export default {
  data() {
    return {
      symbol: '',
      searchQuery: '',
      results: [],
      showSuggestion: false
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

        this.symbol = '';
        window.location.reload();
      } catch (error) {
        console.error('Error:', error);
      }
    },
    searchStocks: debounce(async function () {
      if (this.searchQuery.trim() === ''|| this.searchQuery.length <= 2) {
        this.results = [];
        return;
      }

      try {
        const response = await fetch(`http://localhost:8081/api/dashboards/search/${this.searchQuery}`);
        if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
        const data = await response.json();
        this.results = data[0].bestMatches.map(match => ({
          symbol: match['1. symbol'],
          name: match['2. name']
        }));
      } catch (error) {
        console.error("Error fetching search results:", error);
        this.results = [];
      }
    }, 300)

    ,
    async selectSuggestion(result) {
      this.searchQuery = result.symbol;
      this.showSuggestions = false;
      const url = `http://localhost:8081/api/dashboards/${result.symbol}`;

      try {
        const response = await fetch(url, {
          method: 'POST'
        });

        if (!response.ok) {
          throw new Error('Network response was not ok.');
        }

        this.symbol = '';
        window.location.reload();
      } catch (error) {
        console.error('Error:', error);
      }
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

.choice-form input[type="text"],
.choice-form button {
  width: 100%;
  padding: 0.8rem 1rem;
  font-size: 1rem; 
  border: none;
  border-radius: 4px;
  box-sizing: border-box; 
}

.choice-form button {
  background-color: #4c837a;
  color: #fff;
  cursor: pointer; 
  transition: background-color 0.3s ease; 
}

.choice-form button:hover {
  background-color: #368580;
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

.symbol-container p{
  color: white;
}

</style>