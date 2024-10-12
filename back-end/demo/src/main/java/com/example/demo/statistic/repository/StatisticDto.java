package com.example.demo.statistic.repository;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class StatisticDto {
	
	@Getter
	public static class TrendingLatest {
		
		private String timePeriod;
		
		@Builder
		public TrendingLatest(String timePeriod) {
			this.timePeriod = timePeriod;
		}
	}
	
	
}
