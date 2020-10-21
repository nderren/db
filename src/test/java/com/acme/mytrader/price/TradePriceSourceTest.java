package com.acme.mytrader.price;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TradePriceSourceTest {

    @Mock
    private TradePriceListener tradePriceListener;
    private TradePriceSource tradePriceSource;

    @Before
    public void setupEnvironment() {
        tradePriceSource = new TradePriceSource();
    }

    @Test
    public void sourceWillTransmitToRegisteredListener() {
        tradePriceSource.addPriceListener(tradePriceListener);

        tradePriceSource.transmitPrice("IBM", 1d);

        verify(tradePriceListener, times(1)).priceUpdate("IBM", 1d);
    }

    @Test
    public void sourceWillNotTransmitToUnregisteredListener() {
        tradePriceSource.addPriceListener(tradePriceListener);
        tradePriceSource.removePriceListener(tradePriceListener);

        tradePriceSource.transmitPrice("IBM", 1d);

        verifyNoMoreInteractions(tradePriceListener);
    }

    @Test
    public void sourceWillHandleAnErrorInTransmitting() {

        TradePriceListener errorListener = mock(TradePriceListener.class);
        doThrow(new RuntimeException("Test exception")).when(errorListener).priceUpdate("IBM", 1d);

        tradePriceSource.addPriceListener(errorListener);
        tradePriceSource.addPriceListener(tradePriceListener);
        tradePriceSource.removePriceListener(tradePriceListener);

        tradePriceSource.transmitPrice("IBM", 1d);

        verifyNoMoreInteractions(tradePriceListener);
    }

}