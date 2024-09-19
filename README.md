# stock-dashboard
Application for get a stocks dashboard by symbol in daily interval.

![image](https://github.com/user-attachments/assets/8b74db7d-f8aa-43be-853c-d5aba7d72b1a)


## Functionality
- making stock dashboards by symbol between dates that you choose. 
- viewing stock dashboard and daily open prices.
- downloading stock dashboard in jpeg or png.

## Data
Stock data is provided by: https://polygon.io/

## Technologies
### Backend
- Java 17
- Spring boot
- Hibernate
- H2 database
- Gradle
- Junit5
- Mockito
### Frontend
- VueJS 3
- Canvasjs/vue-stockcharts

## How to run?
1. Generate your API-KEY in https://polygon.io/
2. Set API-KEY in `/stocks-dashboard/BackendModule/src/main/resources/application.properties`
3. Run `stocks-dashboard/BackendModule/src/main/java/pl/sylwestergladki/stocksdashboard/StocksDashboardApplication.java`
4. Change directory `stocks-dashboard/FrontendModule/stocks-dashboard-frontend`
5. Run `npm run serve`

## How to use?
1. Write symbol and click **Find** button.

![image](https://github.com/user-attachments/assets/bb55c43c-e815-4be4-aec3-0e72d6e1ef31)

3. Choose assets that you want.
4. Choose **date from** and **date to**.
5. Click **Submit** button.
6. Now, You have dashboard for asset that you've choose.
7. You can change dateframe, save chart as jpeg/png or delete dashboard.

![image](https://github.com/user-attachments/assets/1232d33f-ba0e-45e1-9b05-d24b1a210507)



