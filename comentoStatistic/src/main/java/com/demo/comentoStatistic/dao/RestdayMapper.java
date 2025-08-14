package com.demo.comentoStatistic.dao;

import com.demo.comentoStatistic.dto.RestdayDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestdayMapper {
    void insertRestday(RestdayDto restdayDto);
}
