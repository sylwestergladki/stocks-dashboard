<template>
  <div>
    <h1>Dashboards</h1>
    <ul>
      <div v-for="dashboard in dashboards" :key="dashboard.id">
        <CanvasJSStockChart :options="dashboard.options" :styles="{ width: '500px', height: '400px', margin: 'auto'}"/>
        <button @click="deleteDashboard(dashboard.id)">Delete Dashboard</button>
      </div>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
import { CanvasJSStockChart } from '@canvasjs/vue-stockcharts';

export default {
  components: {
    CanvasJSStockChart
  },
  data() {
    return {
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
  deleteDashboard(id) {
      axios.delete(`http://localhost:8081/api/dashboards/${id}`)
        .then(() => {
          this.dashboards = this.dashboards.filter(dashboard => dashboard.id !== id);
        })
        .catch(error => {
          console.error('Error deleting dashboard:', error);
        });
    }
}

};
</script>
