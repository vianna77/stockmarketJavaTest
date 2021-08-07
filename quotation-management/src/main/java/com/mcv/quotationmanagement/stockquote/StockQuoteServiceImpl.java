package com.mcv.quotationmanagement.stockquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class StockQuoteServiceImpl implements StockQuoteService{
    StockQuoteRepository stockQuoteRepository;

    @Autowired
    public StockQuoteServiceImpl(StockQuoteRepository theStockQuoteRepository) {
        stockQuoteRepository = theStockQuoteRepository;
    }

    @Override
    public StockQuote findByStockId(String stockId) {
        return stockQuoteRepository.findByStockId(stockId);
    }

    @Override
    public StockQuote save(StockQuote theStockQuote) {
        StockQuote savedStockQuote = null;
        String stockId = theStockQuote.getStockId();
        StockQuote foundStockQuote = findByStockId(stockId);
        if (foundStockQuote == null) {
            savedStockQuote = stockQuoteRepository.save(theStockQuote);
        } else {
            Map<String, String> quotesSaved = foundStockQuote.getQuotes();
            Map<String, String> quotesToSave = theStockQuote.getQuotes();
            Set<String> keysToBeSaved = quotesToSave.keySet();
            for (String keyToBeSaved : keysToBeSaved) {
                if (!quotesSaved.containsKey(keyToBeSaved)) {
                    quotesSaved.put(keyToBeSaved, quotesToSave.get(keyToBeSaved));
                }
            }
            foundStockQuote.setQuotes(quotesSaved);
            savedStockQuote = stockQuoteRepository.save(foundStockQuote);
        }
        return savedStockQuote;
    }
}
