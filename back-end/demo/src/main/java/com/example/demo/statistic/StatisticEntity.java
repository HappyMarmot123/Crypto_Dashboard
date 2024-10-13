package com.example.demo.statistic;

import java.math.BigDecimal;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.example.demo.statistic.repository.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
@Document(collection = "DailyTrend")
public class StatisticEntity {
	
	@MongoId
	@Column(name="_id")
	@Field(name="_id")
    private ObjectId _id;
	
	@Column(name="data")
	@Field(name="data")
	private Data data;
	
	@Field(name="sortTotalVolume")
	private List<TotalVolume> sortTotalVolume;

    public Data getData() {
        return data;
    }
    
    @Getter
    @Setter
    @ToString
    public static class TotalVolume {
        private String k;
        private BigDecimal v;
    }
}
