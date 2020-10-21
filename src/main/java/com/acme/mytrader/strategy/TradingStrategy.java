package com.acme.mytrader.strategy;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {

    private String validSecurity = "IBM";

    private double targetBuyPrice;

    public TradingStrategy(double targetBuyPrice) {
        this.targetBuyPrice = targetBuyPrice;
    }

    public boolean priceBuyLevelReached(String security, double price) {
        return validSecurity.equals(security) && targetBuyPrice >= price;
    }

    public boolean priceSellLevelReached(String security, double price) {
        return validSecurity.equals(security) && targetBuyPrice <= price;
    }
}
