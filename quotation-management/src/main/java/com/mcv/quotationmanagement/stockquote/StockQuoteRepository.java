package com.mcv.quotationmanagement.stockquote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {
    StockQuote findByStockId(String stockId);

}
