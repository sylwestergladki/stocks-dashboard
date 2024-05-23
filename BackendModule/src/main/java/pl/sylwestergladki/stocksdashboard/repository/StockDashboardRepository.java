package pl.sylwestergladki.stocksdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sylwestergladki.stocksdashboard.model.StockDashboard;

public interface StockDashboardRepository extends JpaRepository<StockDashboard, Long> {
}
