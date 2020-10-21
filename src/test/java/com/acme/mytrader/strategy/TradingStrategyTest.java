package com.acme.mytrader.strategy;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TradingStrategyTest {

    private TradingStrategy tradingStrategy;

    @Before
    public void setupEnvironment() {
        tradingStrategy = new TradingStrategy(1d);
    }


    @Test
    public void priceIsInBuyStrategyWhenItIsBelowTarget() {
        assertThat(tradingStrategy.priceBuyLevelReached("IBM", 0d), is(true));
        assertThat(tradingStrategy.priceBuyLevelReached("IBM", -100.00d), is(true));
    }

    @Test
    public void priceIsInSellStrategyWhenItIsAboveTarget() {
        assertThat(tradingStrategy.priceSellLevelReached("IBM", 1d), is(true));
        assertThat(tradingStrategy.priceSellLevelReached("IBM", 10d), is(true));
    }

    @Test
    public void priceIsInIsOutOfStrategyWhenInvalidSecurity() {
        assertThat(tradingStrategy.priceBuyLevelReached("NOTIBM", 0d), is(false));
        assertThat(tradingStrategy.priceSellLevelReached("NOTIBM", 10d), is(false));
    }

}
