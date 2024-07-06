<template>
  <div class="dashboards-container">
    <h1>Dashboards</h1>
    <ul class="dashboard-list">
      <div v-for="dashboard in dashboards" :key="dashboard.id" class="dashboard-item">
        <CanvasJSStockChart :options="dashboard.options" :styles="{ width: '500px', height: '400px', margin: 'auto'}"/>
        <button class="delete-dashboard-button" @click="showDeleteDialog(dashboard.id)">Delete Dashboard</button>
      </div>
    </ul>
  </div>
  <ConfirmationDialog
      :visible="isDialogVisible"
      message="Do you really want to delete this dashboard?"
      @confirm="deleteConfirmed"
      @cancel="closeDialog"
    />
</template>

<script>
import axios from 'axios';
import { CanvasJSStockChart } from '@canvasjs/vue-stockcharts';
import ConfirmationDialog from './ConfirmationDialog.vue'

export default {
  components: {
    CanvasJSStockChart,
    ConfirmationDialog
  },
  data() {
    return {
      isDialogVisible: false,
      dashboards: []
    };
  },
  created() {
    this.fetchDashboards();
  },
  methods: {
  async fetchDashboards() {
    try {
      const response = await axios.get('http://localhost:8081/api/dashboards');
      this.dashboards = response.data;
      this.dashboards.forEach(dashboard => {
        dashboard.options = this.generateChartOptions(dashboard.symbol,dashboard.stockPriceInfo);
      });
    } catch (error) {
      console.error('Error fetching dashboards:', error);
    }
  },
  generateChartOptions(symbol,stockPriceInfo) {
    if (!stockPriceInfo || typeof stockPriceInfo !== 'object') {
      console.warn('Invalid stockPriceInfo:', stockPriceInfo);
      return {};
    }

  
  const sortedData = Object.entries(stockPriceInfo)
    .map(([date, prices]) => ({ date: new Date(date), prices }))
    .sort((a, b) => a.date - b.date);

  const dataPoints = sortedData.map(item => ({
    x: item.date,
    y: item.prices["1. open"] // Use "1. open" price for line chart
  }));

    console.log(stockPriceInfo)

    return {
      title: {
        text: symbol
      },
      charts: [{
        data: [{
          type: "line", // In the future i can use "candlestick"
          dataPoints: dataPoints
        }]
      }],
      navigator: {
        slider: {
          minimum: dataPoints.length > 0 ? dataPoints[0].x : new Date(),
          maximum: dataPoints.length > 0 ? dataPoints[dataPoints.length - 1].x : new Date()
        }
      }
    };
  },
  showDeleteDialog(id) {
      this.dashboardToDelete = id;
      this.isDialogVisible = true;
    },
    closeDialog() {
      this.isDialogVisible = false;
      this.dashboardToDelete = null;
    },
    async deleteConfirmed() {
      try {
        await axios.delete(`http://localhost:8081/api/dashboards/${this.dashboardToDelete}`);
        this.dashboards = this.dashboards.filter(dashboard => dashboard.id !== this.dashboardToDelete);
      } catch (error) {
        console.error('Error deleting dashboard:', error);
      } finally {
        this.closeDialog();
      }
    }
}

};
</script>

<style>

.dashboards-container{
  background-color: rgb(76, 131, 122);
  padding: 1.5rem; 
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-top: 2rem;
  color: white;
}

.dashboard-list{
  display: flex;
  flex-wrap: wrap;
  list-style-type: none;
  padding: 0;
}

.dashboard-item {
  flex: 1 0 300px;
  margin: 10px;
  background-color: #f0f0f0; 
  padding: 1rem;
  border-radius: 1rem;
  max-width: 35rem;
}

.delete-dashboard-button{
  margin-top: 10px;
  padding: 8px 16px;
  background-color: #f44336;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.delete-dashboard-button:hover {
  background-color: #d32f2f; 
}

</style>