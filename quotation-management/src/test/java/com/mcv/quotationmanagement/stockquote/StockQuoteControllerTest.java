package com.mcv.quotationmanagement.stockquote;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcv.quotationmanagement.stock.Stock;
import com.mcv.quotationmanagement.stockcache.StockCacheController;
import com.mcv.quotationmanagement.stockcache.StockCacheService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StockQuoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockCacheController stockCacheController;

    @MockBean
    private StockQuoteService stockQuoteService;

    @MockBean
    private StockCacheService stockCacheService;



    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testSave() throws Exception {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setId("asdf");
        stockQuote.setStockId("petr4");
        Map<String,String> quote = new HashMap<>();
        quote.put("2021-10-01", "11");
        stockQuote.setQuotes(quote);
        String json = mapper.writeValueAsString(stockQuote);
        Mockito.when(stockQuoteService.validateStockId(stockQuote.getStockId())).thenReturn(new Stock());
        Mockito.when(stockQuoteService.save(stockQuote)).thenReturn(stockQuote);
        mockMvc.perform(post("/stockQuotes/save").
                contentType(MediaType.APPLICATION_JSON).
                characterEncoding("utf-8").
                content(json).
                accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }

    @Test
    public void testSaveAll() throws Exception {
        StockQuote stockQuote = new StockQuote();
        stockQuote.setId("asdf");
        stockQuote.setStockId("petr4");
        Map<String,String> quote = new HashMap<>();
        quote.put("2021-10-01", "11");
        stockQuote.setQuotes(quote);
        List<StockQuote> list = new ArrayList<StockQuote>();
        list.add(stockQuote);
        list.add(stockQuote);
        String json = mapper.writeValueAsString(list);
        Mockito.when(stockQuoteService.validateStockId(stockQuote.getStockId())).thenReturn(new Stock());
        Mockito.when(stockQuoteService.save(stockQuote)).thenReturn(stockQuote);
        mockMvc.perform(post("/stockQuotes/saveAll").
                        contentType(MediaType.APPLICATION_JSON).
                        characterEncoding("utf-8").
                        content(json).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
    }
}
