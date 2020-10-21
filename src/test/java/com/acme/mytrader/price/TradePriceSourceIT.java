package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;
import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TradePriceSourceIT {

    private TradePriceSource tradePriceSource;

    @Before
    public void setupEnvironment() {
        tradePriceSource = new TradePriceSource();
    }

    @Test
    public void newPriceWillTriggerBuyAction() {

        final List<Double> priceReceived = new ArrayList<>();

        PriceListener priceListener = new TradePriceListener(new TradingStrategy(100d), new ExecutionService() {
            @Override
            public void buy(String security, double price, int volume) {
                priceReceived.add(price);
            }

            @Override
            public void sell(String security, double price, int volume) {
                throw new NotImplementedException();
            }
        });
        tradePriceSource.addPriceListener(priceListener);

        tradePriceSource.transmitPrice("IBM", 50d);

        assertThat(priceReceived.size(), is(1));
        assertThat(priceReceived.get(0), is(50d));

    }

    @Test
    public void newPriceWillTriggerSellAction() {

        final List<Double> priceReceived = new ArrayList<>();

        PriceListener priceListener = new TradePriceListener(new TradingStrategy(100d), new ExecutionService() {
            @Override
            public void buy(String security, double price, int volume) {
                throw new NotImplementedException();
            }

            @Override
            public void sell(String security, double price, int volume) {
                priceReceived.add(price);
            }
        });
        tradePriceSource.addPriceListener(priceListener);

        tradePriceSource.transmitPrice("IBM", 150d);

        assertThat(priceReceived.size(), is(1));
        assertThat(priceReceived.get(0), is(150d));
    }

    @Test
    public void newPriceForInvalidSecurityWillNotTriggerAnyAction() {

        PriceListener priceListener = new TradePriceListener(new TradingStrategy(100d), new ExecutionService() {
            @Override
            public void buy(String security, double price, int volume) {
                throw new NotImplementedException();
            }

            @Override
            public void sell(String security, double price, int volume) {
                throw new NotImplementedException();
            }
        });
        tradePriceSource.addPriceListener(priceListener);

        tradePriceSource.transmitPrice("NOTIBM", 150d);
    }

}