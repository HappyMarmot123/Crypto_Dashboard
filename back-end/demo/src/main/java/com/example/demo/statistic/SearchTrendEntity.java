package com.example.demo.statistic;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
@Document(collection = "SearchTrend")
public class SearchTrendEntity {
	
	@MongoId
    public String _id;
    public Coin[] coins;
    private LocalDateTime updated_at;
    

    public static class Coin {
        public Item item;
    }

    @Getter
    @ToString
    public static class Item {
        public String small;
        public String symbol;
        public int score;
        public long coinId;
        public String large;
        public Data data;
        public String thumb;
        public String name;
        public int marketCapRank;
        public String id;
        public double priceBtc;
        public String slug;
    }

    @Getter
    @ToString
    public static class Data {
        public String totalVolume;
        public String marketCap;
        public double price;
        public String marketCapBtc;
        public String priceBtc;
        public String totalVolumeBtc;
        public String sparkline;
        public String content;
    }
}
