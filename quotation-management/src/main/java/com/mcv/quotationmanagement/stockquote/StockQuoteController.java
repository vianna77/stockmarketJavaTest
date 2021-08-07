package com.mcv.quotationmanagement.stockquote;

import com.mcv.quotationmanagement.stock.Stock;
import com.mcv.quotationmanagement.stockquote.exception.NoSuchElementFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stockQuotes")
public class StockQuoteController {

    private StockQuoteService stockQuoteService;

    @Autowired
    public StockQuoteController(StockQuoteService theStockQuoteService) {
        stockQuoteService = theStockQuoteService;
    }

    @GetMapping("/findByStockId/{stockId}")
    public StockQuote findByStockId(@PathVariable String stockId) {
        return stockQuoteService.findByStockId(stockId);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    @PostMapping("/save")
    public StockQuote save(@RequestBody StockQuote theStockQuote) {
        StockQuote savedStockQuote = null;
        stockQuoteService.validateStockId(theStockQuote.getStockId());
        savedStockQuote = stockQuoteService.save(theStockQuote);
        return savedStockQuote;
   }

    @PostMapping("/saveAll")
    public List<StockQuote> saveAll(@RequestBody List<StockQuote> theStockQuotes) {
        List<StockQuote> savedList = new ArrayList<StockQuote>();
        for (StockQuote stockQuote : theStockQuotes) {
            StockQuote saved = save(stockQuote);
            savedList.add(saved);
        }
        return savedList;
    }
}
