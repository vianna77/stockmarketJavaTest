package com.mcv.quotationmanagement.stockcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StockCacheServiceImpl implements StockCacheService{
    private Logger logger = LoggerFactory.getLogger(StockCacheServiceImpl.class);

    @Value("${stock.manager.notification}")
    private String stockManagerNotification;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void registerToStockManager() {
        String url = stockManagerNotification;
        StockCacheNotification notification = new StockCacheNotification();
        notification.setHost("localhost");
        notification.setPort(serverPort);
        List<StockCacheNotification> listResponse = restTemplate.postForObject(url, notification, List.class);
        if (listResponse != null) {
            logger.debug(listResponse.toString());
        }
    }
}
