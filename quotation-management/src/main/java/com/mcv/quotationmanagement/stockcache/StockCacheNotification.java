package com.mcv.quotationmanagement.stockcache;

public class StockCacheNotification {
    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}
