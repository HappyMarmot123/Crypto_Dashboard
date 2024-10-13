package com.example.demo.statistic;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
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
    public ObjectId _id;
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
        public int market_cap_rank;
        public String id;
        public double priceBtc;
        public String slug;
    }

    @Getter
    @ToString
    public static class Data {
        public String total_volume;
        public String market_cap;
        public double price;
        public String market_cap_btc;
        public String price_btc;
        public String total_volume_btc;
        public String sparkline;
        public String content;
    }
}
