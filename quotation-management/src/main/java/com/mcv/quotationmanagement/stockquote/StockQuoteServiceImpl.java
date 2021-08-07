package com.mcv.quotationmanagement.stockquote;

import com.mcv.quotationmanagement.stock.Stock;
import com.mcv.quotationmanagement.stockquote.exception.NoSuchElementFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Service
public class StockQuoteServiceImpl implements StockQuoteService{
    private StockQuoteRepository stockQuoteRepository;

    @Value("${stock.manager.api}")
    private String stockManagerApi;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public void validateStockId(String stockId) {
        String url = stockManagerApi + stockId;
        Stock stock = restTemplate.getForObject(url, Stock.class);
        if (stock == null) {
            throw new NoSuchElementFoundException(HttpStatus.PRECONDITION_FAILED, "stock id not found - " + stockId);
        }
    }
}
