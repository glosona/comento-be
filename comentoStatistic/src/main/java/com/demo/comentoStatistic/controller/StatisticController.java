package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.DayAverageCountDto;
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
    public ResponseEntity<LoginCountDto> getLoginCount(
            @RequestParam String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day
            ) {

        return ResponseEntity.ok(statisticService.getLogins(year, month, day));
    }

    @GetMapping("department/{department}")
    public Object getLoginCountByDepartment(
            @PathVariable String department,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) String day
    ){

        return ResponseEntity.ok(statisticService.getByDepartmentLogins(year, month, day, department));
    }

    @GetMapping("dayAverage")
    public ResponseEntity<DayAverageCountDto> getDayAverage() {

        return ResponseEntity.ok(statisticService.getDayAverageLogins());
    }


    @GetMapping("nonRestday")
    public ResponseEntity<NonRestdayCountDto>  getNonRestdayLoginCount(
            @RequestParam String fromDate,
            @RequestParam String toDate) {

        return ResponseEntity.ok(statisticService.getNonRestdayLogins(fromDate, toDate));
    }

    @GetMapping("nonRestday/all")
    public ResponseEntity<NonRestdayCountDto> getAllNonRestdayLoginCount() {

        return ResponseEntity.ok(statisticService.getAllNonRestdayLogins());
    }
}