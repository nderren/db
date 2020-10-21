package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.strategy.TradingStrategy;

public class TradePriceListener implements PriceListener {


    private TradingStrategy tradingStrategy;
    private ExecutionService executionService;


    public TradePriceListener(TradingStrategy tradingStrategy, ExecutionService executionService) {
        this.tradingStrategy = tradingStrategy;
        this.executionService = executionService;
    }

    public void priceUpdate(String security, double price) {
        if (tradingStrategy.priceBuyLevelReached(security, price)) {
            executionService.buy(security, price, 1);
        } else if (tradingStrategy.priceSellLevelReached(security, price)) {
            executionService.sell(security, price, 1);
        }
    }
}
