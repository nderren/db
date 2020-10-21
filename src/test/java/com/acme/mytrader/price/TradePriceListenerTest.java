package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradePriceListenerTest {

    @Mock
    private TradingStrategy tradingStrategy;
    @Mock
    private ExecutionService executionService;

    private TradePriceListener tradePriceListener;

    @Before
    public void setupEnvironment() {
        tradePriceListener = new TradePriceListener(tradingStrategy, executionService);
    }

    @Test
    public void priceInBuyStrategyWillTriggerBuyAction() {
        when(tradingStrategy.priceBuyLevelReached("IBM", 1.0d)).thenReturn(true);

        tradePriceListener.priceUpdate("IBM", 1.0d);

        verify(executionService, times(1)).buy("IBM", 1.0d, 1);
    }

    @Test
    public void priceOutOfBuyStrategyWillNotTriggerBuyAction() {
        when(tradingStrategy.priceBuyLevelReached("IBM", 1.0d)).thenReturn(false);

        tradePriceListener.priceUpdate("IBM", 1.0d);

        verifyNoMoreInteractions(executionService);
    }

    @Test
    public void priceInSellStrategyWillTriggerSellAction() {
        when(tradingStrategy.priceSellLevelReached("IBM", 1.0d)).thenReturn(true);

        tradePriceListener.priceUpdate("IBM", 1.0d);

        verify(executionService, times(1)).sell("IBM", 1.0d, 1);
    }

    @Test
    public void priceOutOfSellStrategyWillNotTriggerSellAction() {
        when(tradingStrategy.priceSellLevelReached("IBM", 1.0d)).thenReturn(false);

        tradePriceListener.priceUpdate("IBM", 1.0d);

        verifyNoMoreInteractions(executionService);
    }

}