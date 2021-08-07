package com.mcv.quotationmanagement.stockquote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockQuoteRepository extends JpaRepository<StockQuote, String> {
    List<StockQuote> findByStockId(String stockId);
}
