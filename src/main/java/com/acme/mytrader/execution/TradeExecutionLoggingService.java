package com.acme.mytrader.execution;


import java.util.logging.Logger;

public class TradeExecutionLoggingService implements ExecutionService {

    private Logger logger = Logger.getLogger("TradeExecutionLoggingService");

    public void buy(String security, double price, int volume) {
        logger.info(String.format("Buy on %s %s %s", security, price, volume));
    }

    public void sell(String security, double price, int volume) {
        logger.info(String.format("Sell on %s %s %s", security, price, volume));
    }
}
