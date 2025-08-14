package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.YearCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logins")
public class StatisticController {

    @Autowired
    StatisticService statisticService;


    @RequestMapping(value = "/{year}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<YearCountDto> getYearLoginCount(@PathVariable("year") String year) {

        return ResponseEntity.ok(statisticService.getYearLogins(year));
    }

    @RequestMapping(value = "/{year}/{month}", produces = "application/json")
    @ResponseBody
    public Object getYearMonthLoginCount(@PathVariable("year") String year, @PathVariable("month") String month) {

        return ResponseEntity.ok(statisticService.getYearMonthLogins(year, month));
    }

    @GetMapping("/nonRestday")
    public Object getNonRestdayLoginCount(
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        return ResponseEntity.ok(statisticService.getNonRestdayLogins(fromDate, toDate));
    }

    @GetMapping("/nonRestday/all")
    public Object getAllNonRestdayLoginCount() {

        return ResponseEntity.ok(statisticService.getAllNonRestdayLogins());
    }
}