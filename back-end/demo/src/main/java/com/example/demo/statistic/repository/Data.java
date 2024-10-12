package com.example.demo.statistic.repository;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Document
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Data {
    @Field(name="active_cryptocurrencies")
    private int activeCryptocurrencies;

    @Field(name="markets")
    private int markets;

    @Field(name="total_market_cap")
    private Map<String, BigDecimal> totalMarketCap;

    @Field(name="total_volume")
    private Map<String, BigDecimal> totalVolume;

    @Field(name="market_cap_percentage")
    private Map<String, BigDecimal> marketCapPercentage;

    @Field(name="market_cap_change_percentage_24h_usd")
    private double marketCapChangePercentage24hUsd;

    @Field(name="updated_at")
    private long updatedAt;
    
    @Builder
    public Data(int activeCryptocurrencies,
                int markets, Map<String, BigDecimal> totalMarketCap, Map<String, BigDecimal> totalVolume, 
                Map<String, BigDecimal> marketCapPercentage, double marketCapChangePercentage24hUsd, 
                long updatedAt) {
        this.activeCryptocurrencies = activeCryptocurrencies;
        this.markets = markets;
        this.totalMarketCap = totalMarketCap;
        this.totalVolume = totalVolume;
        this.marketCapPercentage = marketCapPercentage;
        this.marketCapChangePercentage24hUsd = marketCapChangePercentage24hUsd;
        this.updatedAt = updatedAt;
    }
}
