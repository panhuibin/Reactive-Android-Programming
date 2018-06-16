package com.example.span.stocksample;

import com.example.span.stocksample.yahoo.json.YahooStockQuote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StockUpdateEntry implements Serializable {

    private final String stockSymbol;
    private final BigDecimal stockPrice;
    private final Date date;


    public StockUpdateEntry(String stockSymbol, BigDecimal stockPrice, Date date) {
        this.stockSymbol = stockSymbol;
        this.stockPrice = stockPrice;
        this.date = date;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public Date getDate() {
        return date;
    }

    public static StockUpdateEntry create(YahooStockQuote quote) {
        return new StockUpdateEntry(quote.getSymbol(), quote.getLastTradePriceOnly(), new Date());
    }

}
