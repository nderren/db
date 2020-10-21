package com.acme.mytrader;

import com.acme.mytrader.execution.TradeExecutionLoggingService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.TradePriceListener;
import com.acme.mytrader.price.TradePriceSource;
import com.acme.mytrader.strategy.TradingStrategy;

public class ApplicationMain {

    public static void main(String[] args) {

        if (args.length != 2) {
            return;
        }

        String security = args[0];
        double price = Double.parseDouble(args[1]);

        PriceListener priceListener = new TradePriceListener(new TradingStrategy(100d), new TradeExecutionLoggingService());

        TradePriceSource tradePriceSource = new TradePriceSource();

        tradePriceSource.addPriceListener(priceListener);

        tradePriceSource.transmitPrice(security, price);

    }
}
