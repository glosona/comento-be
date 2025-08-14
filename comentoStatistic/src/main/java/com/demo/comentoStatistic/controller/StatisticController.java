package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.LoginCountDto;
import com.demo.comentoStatistic.dto.NonRestdayCountDto;
import com.demo.comentoStatistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logins")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @GetMapping()
    public ResponseEntity<LoginCountDto>  getLoginCount(
            @RequestParam String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day
            ) {

        if (month == null && day == null) {
            return ResponseEntity.ok(statisticService.getYearLogins(year));
        } else if (day == null) {
            return ResponseEntity.ok(statisticService.getYearMonthLogins(year, month));
        } else {
            return ResponseEntity.ok(statisticService.getYearMonthDayLogins(year, month, day));
        }
    }

//    @GetMapping("department")
//    public Object getLoginCountByDepartment(
//            @RequestParam String name,
//            @RequestParam(required = false) String year,
//            @RequestParam(required = false, defaultValue = "01") String month,
//            @RequestParam(required = false, defaultValue = "01") String day
//    ){
//
//    }


    @GetMapping("/nonRestday")
    public ResponseEntity<NonRestdayCountDto>  getNonRestdayLoginCount(
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        return ResponseEntity.ok(statisticService.getNonRestdayLogins(fromDate, toDate));
    }

    @GetMapping("/nonRestday/all")
    public ResponseEntity<NonRestdayCountDto> getAllNonRestdayLoginCount() {

        return ResponseEntity.ok(statisticService.getAllNonRestdayLogins());
    }
}