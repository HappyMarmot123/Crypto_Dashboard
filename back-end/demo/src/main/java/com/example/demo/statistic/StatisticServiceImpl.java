package com.example.demo.statistic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ObjectOperators.ObjectToArray;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.component.CoinGeckoApi;
import com.example.demo.component.ScheduledTasks;
import com.example.demo.statistic.repository.AverageResult;
import com.example.demo.statistic.repository.DataWrapper;

import lombok.RequiredArgsConstructor;
 

@RequiredArgsConstructor
@Service
public class StatisticServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private final CoinGeckoApi coinGeckoApi;
	private final MongoTemplate mongoTemplate;
	
    public void requestApi() {
        List<String> urls = new ArrayList<>();
        urls.add("https://api.coingecko.com/api/v3/global");
        urls.add("https://api.coingecko.com/api/v3/search/trending");
        
        List<String> collectionNames = new ArrayList<>();
        collectionNames.add("DailyTrend");
        collectionNames.add("SearchTrend");
        
    	try {
    		List<String> responseData = coinGeckoApi.fetchGlobalData(urls);
            for (int i = 0; i < responseData.size(); i++) {
                String response = responseData.get(i);
                String collection = collectionNames.get(i);

                mongoTemplate.insert(response, collection);
                log.info("Insert response into {} at {}", collection, dateFormat.format(new Date()));
            }
        } catch (Exception e) {
        	log.error("COIN GECKO API **FAILED** at {}", dateFormat.format(new Date()));
            e.printStackTrace();
        }
    }
    
    public HashMap getStatistic() {
    	try {
            Query query = new Query();	
            query.with(Sort.by(Sort.Order.desc("updated_at"))) // 필드 이름에 맞춰 수정
            .limit(1);
            SearchTrendEntity result4 = mongoTemplate.findOne(query, SearchTrendEntity.class);
    		
//          db.DailyTrend.aggregate([
//          { "$match": { "_id": ObjectId("6707ebf06d64380aefe02d35") }},
//          { "$project": { totalMarketCap: { "$objectToArray": "$data.total_market_cap" }}},
//          { "$unwind": "$totalMarketCap" },
//          { "$group": {
//              _id: "average",
//              averageTotalMarketCap: { "$avg": "$totalMarketCap.v" }
//          }}
//        ])

            Aggregation aggregation = Aggregation.newAggregation(
            	    Aggregation.sort(Sort.by(Sort.Order.desc("data.updated_at"))),
            	    Aggregation.limit(1),
            	    Aggregation.project("_id", "data")
            	        .and(ObjectToArray.valueOfToArray("data.total_volume")).as("totalVolume"),
            	    Aggregation.unwind("totalVolume"),  // totalMarketCap 배열을 펼침
            	    Aggregation.sort(Sort.by(Sort.Order.desc("totalVolume.v"))),  // totalMarketCap.v 기준으로 정렬
            	    Aggregation.group("_id")  // 그룹화
            	        .first("data").as("data")
            	        .push("totalVolume").as("sortTotalVolume")  // 정렬된 totalMarketCap 배열 저장
            	);

            AggregationResults<StatisticEntity> dataList = mongoTemplate.aggregate(aggregation, "DailyTrend", StatisticEntity.class);

	         List<StatisticEntity> dataLists = dataList.getMappedResults();
	         Object result = dataLists.get(0);
         
         
         
	         Aggregation aggregation2 = Aggregation.newAggregation(
                 Aggregation.sort(Sort.by(Sort.Order.desc("data.updated_at"))), // 최신순 정렬
                 Aggregation.limit(7), // 최대 7개로 제한
                 Aggregation.project("data.market_cap_change_percentage_24h_usd") // 필요한 필드만 선택
                     .and("data.market_cap_change_percentage_24h_usd").as("marketCapChangePercentage24hUsd") // 필드 재명명
                     .and("data.updated_at").as("updatedAt") // 필드 재명명
             );

             AggregationResults<DataWrapper> percentage = mongoTemplate.aggregate(aggregation2, "DailyTrend", DataWrapper.class);

             List<Map<String, Object>> result2 = percentage.getMappedResults().stream()
            		    .map(dataWrapper -> {
            		        Map<String, Object> resultMap = new HashMap<>();
            		        resultMap.put("capChange", dataWrapper.getMarketCapChangePercentage24hUsd()); 
            		        resultMap.put("updatedAt", dataWrapper.getUpdatedAt());
            		        return resultMap;
            		    })
            		    .collect(Collectors.toList());
         
         
         
         
            Aggregation aggregation3 = Aggregation.newAggregation(
//                  Aggregation.match(Criteria.where("_id").is(new org.bson.types.ObjectId(id))),
            		Aggregation.sort(Sort.by(Sort.Order.desc("data.updated_at"))),
            		Aggregation.limit(1),
                    Aggregation.project("data.total_market_cap")
                            .and(ObjectToArray.valueOfToArray("data.total_market_cap")).as("totalMarketCap"),
                    Aggregation.unwind("totalMarketCap"),
                    Aggregation.group()
                            .avg("totalMarketCap.v").as("averageTotalMarketCap")
            );

            AggregationResults<AverageResult> averages = mongoTemplate.aggregate(aggregation3, "DailyTrend", AverageResult.class);
            List<AverageResult> average = averages.getMappedResults();
            Object result3 = average.get(0);
            

            
            HashMap data = new HashMap<>();
            data.put("resultList", result); 
            data.put("capPercentage7days", result2);
            data.put("capAverage", result3);
            data.put("searchTrend", result4); 
            
            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
}