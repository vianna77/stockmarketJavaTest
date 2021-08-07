package com.mcv.quotationmanagement.stockquote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<StockQuote> findByStockId(@PathVariable String stockId) {
        return stockQuoteService.findByStockId(stockId);
    }
}
