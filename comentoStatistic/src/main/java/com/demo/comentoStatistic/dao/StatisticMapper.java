package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface StatisticMapper {

    YearCountDto selectYearLogin(String year);  // 연도별 접속자 수
    YearMonthCountDto selectYearMonthLogin(String yearMonth);   // 월별 접속자 수
    DayCountDto selectYearMonthDayLogin(String yearMonthDay);   // 일자별 접속자 수

    DayAverageCountDto selectDayAverageLogin();     // 평균 하루 로그인 수

    MonthByDepartmentDto selectMonthByDepartmentLogin(String yearMonth, String department);     // 부서별 월별 로그인 수

    NonRestdayCountDto selectNonRestdayLogin(String fromDate, String toDate);      // 기간 내 휴일 제외 로그인 수
    Map<String, String> selectMinMaxCreateDate();      // 가장 오래된/최근 로그인 기록
}