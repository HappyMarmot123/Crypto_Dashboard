package com.example.demo.component;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.statistic.StatisticServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ScheduledTasks {

	private final StatisticServiceImpl statisticServiceImpl;

	@Scheduled(cron = "0 0 0 * * ?") // 매일 자정 00:00:00에 실행
	public void reportCurrentTime() {
		statisticServiceImpl.requestApi();
		statisticServiceImpl.deleteStatistic();
	}
}
