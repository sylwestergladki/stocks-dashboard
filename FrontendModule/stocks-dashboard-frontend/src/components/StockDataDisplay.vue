<!-- src/components/DashboardList.vue -->
<template>
  <div>
    <h1>Dashboards</h1>
    <ul>
      <div v-for="dashboard in dashboards" :key="dashboard.id">
        ID:{{ dashboard.id }}  Symbol: {{ dashboard.symbol }}
        
        <button @click="fetchDashboardData(dashboard.id)">Refresh data</button>



      </div>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
import StockChart from './StockChart.vue';

export default {
  components: {
    StockChart,
  },
  data() {
    return {
      dashboards: [],
      stockData: []
    };
  },
  created() {
    this.fetchDashboards();
    this.interval = setInterval(this.fetchDashboards, 5000);
    this.interval = setInterval(this.fetchDashboardsData, 5000);
  },
  methods: {
    fetchDashboards() {
      axios.get('http://localhost:8081/dashboards')
        .then(response => {
          console.log(response)
          this.dashboards = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    fetchDashboardData(dashboardId) {
    axios.get(`http://localhost:8081/dashboards/${dashboardId}`)
      .then(response => {
        console.log(response);        
        this.dashboardData = response.data;
      })
      .catch(error => {
        console.log(`Error fetching data for dashboard ID ${dashboardId}:`, error);
      });
    },
    beforeDestroy() {
    clearInterval(this.interval);
  }
  }
};
</script>
