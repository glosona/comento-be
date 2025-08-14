package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.dao.StatisticMapper;
import com.demo.comentoStatistic.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    public YearCountDto getYearLogins(String year){

        return statisticMapper.selectYearLogin(year);
    }

    public YearMonthCountDto getYearMonthLogins(String year, String month){

        return statisticMapper.selectYearMonthLogin(year+month);
    }

    public DayCountDto getYearMonthDayLogins(String year, String month, String day){

        return statisticMapper.selectYearMonthDayLogin(year+month+day);
    }

    public DayAverageCountDto getDayAverageLogins(){

        return statisticMapper.selectDayAverageLogin();
    }

    public MonthByDepartmentDto getMonthByDepartmentLogins(String year, String month, String department){

        return statisticMapper.selectMonthByDepartmentLogin(year+month, department);
    }

    public NonHolidayCountDto getNonHolidayLogins(String year){

        return statisticMapper.selectNonHolidayLogin(year);
    }

}