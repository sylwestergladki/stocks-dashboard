package pl.sylwestergladki.stocksdashboard.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sylwestergladki.stocksdashboard.stockDataClient.StockDataClient;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SymbolServiceImplTest {

    @Mock
    private StockDataClient stockDataClient;
    @InjectMocks
    private SymbolServiceImpl symbolService;

    @Test
    public void symbolService_getSymbolMatch_correctSymbol_ReturnsSymbolInfo(){
        String symbol = "AaPL";

        when(stockDataClient.getSymbolInfo(anyString())).thenReturn(TickerResponse.builder().build());
        TickerResponse symbolMatch = symbolService.getSymbolMatch(symbol);

        Assertions.assertThat(symbolMatch).isNotNull();
        verify(stockDataClient, times(1)).getSymbolInfo(symbol);
    }

    @Test
    public void symbolService_getSymbolMatch_invalidSymbol_ReturnsSymbolInfo(){
        String symbol = "aa%%";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            symbolService.getSymbolMatch(symbol);
        });

        Assertions.assertThat("Invalid stock symbol: " + symbol).isEqualTo(exception.getMessage());
        verify(stockDataClient, times(0)).getSymbolInfo(symbol);
    }


}