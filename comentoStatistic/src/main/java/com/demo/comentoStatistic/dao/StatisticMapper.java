package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);  // 연도별 접속자 수
    YearMonthCountDto selectYearMonthLogin(String yearMonth);   // 월별 접속자 수
    DayCountDto selectYearMonthDayLogin(String yearMonthDay);   // 일자별 접속자 수

    DayAverageCountDto selectDayAverageLogin();     // 평균 하루 로그인 수

    MonthByDepartmentDto selectMonthByDepartmentLogin(String yearMonth, String department);     // 부서별 월별 로그인 수

    NonHolidayCountDto selectNonHolidayLogin(String year);      // 휴일 제외 로그인 수


}