package com.acme.mytrader.price;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TradePriceSource implements PriceSource {

    private Logger logger = Logger.getLogger("TradePriceSource");
    private List<PriceListener> listeners = new ArrayList<>();


    public void addPriceListener(PriceListener listener) {
        listeners.add(listener);
    }

    public void removePriceListener(PriceListener listener) {
        listeners.remove(listener);
    }

    public void transmitPrice(final String security, double price) {
        listeners.forEach(listener -> {
            try {
                listener.priceUpdate(security, price);
            } catch (RuntimeException e) {
                logger.severe(String.format("Failed to transmit %s %s to listener %s", security, price, listener.toString()));
            }
        });
    }
}
