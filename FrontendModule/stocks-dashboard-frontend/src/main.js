import { createApp } from 'vue';
import App from './App.vue';
import CanvasJSStockChart from '@canvasjs/vue-stockcharts'; // Import pluginu

const app = createApp(App);
app.use(CanvasJSStockChart); // Zarejestruj plugin
app.mount('#app');
