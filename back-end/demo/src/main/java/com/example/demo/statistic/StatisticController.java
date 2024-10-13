package com.example.demo.statistic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.RequiredArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/statistic")
public class StatisticController {
	
    private final StatisticServiceImpl statisticService;

    @GetMapping("/")
    public ResponseEntity<?>  getStatistic() {
    	ResponseEntity results =new ResponseEntity<>(statisticService.getStatistic(), HttpStatus.OK);
        return results;
    }
	
	@GetMapping("/request")
	public void requestApi() {
		statisticService.requestApi();
	}
	
	@GetMapping("/delete")
	public void deleteDailyTrend() {
		statisticService.deleteStatistic();
	}
	
}