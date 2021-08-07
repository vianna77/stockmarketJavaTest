package com.mcv.quotationmanagement.stockquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stockQuotes")
public class StockQuoteController {

    StockQuoteService stockQuoteService;

    @Autowired
    public StockQuoteController(StockQuoteService theStockQuoteService) {
        stockQuoteService = theStockQuoteService;
    }

    @GetMapping("/findByStockId/{stockId}")
    public StockQuote findByStockId(@PathVariable String stockId) {
        return stockQuoteService.findByStockId(stockId);
    }

    @PostMapping("/save")
    public StockQuote save(@RequestBody StockQuote theStockQuote) {
        StockQuote savedStockQuote = null;
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
