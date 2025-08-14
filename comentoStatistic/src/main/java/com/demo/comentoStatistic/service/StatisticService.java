package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    @Autowired
    RestdayService restdayService;

    public LoginCountDto getLogins(String year, String month, String day) {

        if (month == null && day == null) {
            return statisticMapper.selectYearLogin(year);
        } else if (day == null) {
            return statisticMapper.selectYearMonthLogin(year, month);
        } else {
            return statisticMapper.selectYearMonthDayLogin(year, month, day);
        }
    }

    public DayAverageCountDto getDayAverageLogins() {

        return statisticMapper.selectDayAverageLogin();
    }

    public MonthByDepartmentDto getMonthByDepartmentLogins(String year, String month, String department) {

        return statisticMapper.selectMonthByDepartmentLogin(year + month, department);
    }

    public NonRestdayCountDto getNonRestdayLogins(String fromDate, String toDate) {
        restdayService.getRestdaysInRange(fromDate, toDate);

        return statisticMapper.selectNonRestdayLogin(fromDate, toDate);
    }

    public NonRestdayCountDto getAllNonRestdayLogins() {
        Map<String, String> dateMap = statisticMapper.selectMinMaxCreateDate();
        String fromDate = dateMap.get("fromDate");
        String toDate = dateMap.get("toDate");
        restdayService.getRestdaysInRange(fromDate, toDate);

        return statisticMapper.selectNonRestdayLogin(fromDate, toDate);
    }
}