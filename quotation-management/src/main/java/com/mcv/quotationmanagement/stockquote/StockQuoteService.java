package com.mcv.quotationmanagement.stockquote;

import java.util.List;

public interface StockQuoteService {
    List<StockQuote> findByStockId(String stockId);
}
