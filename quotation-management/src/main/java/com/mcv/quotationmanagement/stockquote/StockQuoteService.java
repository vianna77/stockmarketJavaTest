package com.mcv.quotationmanagement.stockquote;

public interface StockQuoteService {
    StockQuote findByStockId(String stockId);
    StockQuote save(StockQuote stockQuote);
    void validateStockId(String stockId);
}
