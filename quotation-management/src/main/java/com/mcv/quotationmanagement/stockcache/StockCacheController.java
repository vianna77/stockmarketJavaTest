package com.mcv.quotationmanagement.stockcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/stockcache")
public class StockCacheController {
    private Logger logger = LoggerFactory.getLogger(StockCacheController.class);
    private StockCacheService stockCacheService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    public StockCacheController(StockCacheService theStockCacheService){
        stockCacheService = theStockCacheService;
    }

    @DeleteMapping
    void deleteCache() {
        for(String name:cacheManager.getCacheNames()){
            cacheManager.getCache(name).clear();
        }
        logger.debug("Deleted stocks cache.");
    }

    @PostConstruct
    void postConstruct(){
        stockCacheService.registerToStockManager();
    }
}
