package pl.sylwestergladki.stocksdashboard.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.sylwestergladki.stocksdashboard.service.SymbolServiceImpl;
import pl.sylwestergladki.stocksdashboard.stockDataClient.dto.TickerResponse;


import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SymbolControllerTest {

    @Mock
    private SymbolServiceImpl symbolService;

    @InjectMocks
    private SymbolController symbolController;

    @Test
    void SymbolController_getSymbolMatch_returnsTickerResponse() {
        String symbol = "AAPL";
        TickerResponse expectedResponse = TickerResponse.builder().build();
        when(symbolService.getSymbolMatch(symbol)).thenReturn(expectedResponse);

        ResponseEntity<TickerResponse> response = symbolController.getSymbolData(symbol);

        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        Assertions.assertThat(expectedResponse).isEqualTo(response.getBody());
        verify(symbolService, times(1)).getSymbolMatch(symbol);
    }

    @Test
    void testGetSymbolData_NotFound() {
        String symbol = "INVALID";
        when(symbolService.getSymbolMatch(symbol)).thenReturn(null);

        ResponseEntity<TickerResponse> response = symbolController.getSymbolData(symbol);

        Assertions.assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        Assertions.assertThat(response.getBody()).isNull();
        verify(symbolService, times(1)).getSymbolMatch(symbol);
    }
}
