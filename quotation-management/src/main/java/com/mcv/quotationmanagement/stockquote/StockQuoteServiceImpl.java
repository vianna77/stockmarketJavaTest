package com.mcv.quotationmanagement.stockquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockQuoteServiceImpl implements StockQuoteService{
    StockQuoteRepository stockQuoteRepository;

    @Autowired
    public StockQuoteServiceImpl(StockQuoteRepository theStockQuoteRepository) {
        stockQuoteRepository = theStockQuoteRepository;
    }

    @Override
    public List<StockQuote> findByStockId(String stockId) {
        return stockQuoteRepository.findByStockId(stockId);
    }
}
