package com.mcv.quotationmanagement.stockquote;

import com.mcv.quotationmanagement.stock.Stock;

public interface StockQuoteService {
    StockQuote findByStockId(String stockId);
    StockQuote save(StockQuote stockQuote);
    Stock validateStockId(String stockId);
}
