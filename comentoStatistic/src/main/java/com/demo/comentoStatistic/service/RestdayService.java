package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.config.RestdayApiConfig;
import com.demo.comentoStatistic.dao.RestdayMapper;
import com.demo.comentoStatistic.dto.RestdayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class RestdayService {

    @Autowired
    RestdayMapper restdayMapper;

    private final RestTemplate restTemplate;
    private final RestdayApiConfig restdayApiConfig;

    public void getRestdaysInRange(String fromDate, String toDate){
        YearMonth start = YearMonth.of(
                Integer.parseInt(fromDate.substring(0, 4)),
                Integer.parseInt(fromDate.substring(4, 6))
        );

        YearMonth end = YearMonth.of(
                Integer.parseInt(toDate.substring(0, 4)),
                Integer.parseInt(toDate.substring(4, 6))
        );

        YearMonth current = start;
        while(!current.isAfter(end)){
            getRestdayItems(
                    Integer.toString(current.getYear()),
                    String.format("%02d", current.getMonthValue())
            );
            current = current.plusMonths(1);
        }

    }

    public RestdayDto getRestdayItems(String year, String month) {
        System.out.println("getRestdayItems"+year+":"+month);
        URI uri = UriComponentsBuilder
                .fromUriString(restdayApiConfig.getUrl() + "/getHoliDeInfo")
                .queryParam("solYear", year)
                .queryParam("solMonth", month)
                .queryParam("ServiceKey", restdayApiConfig.getKey())
                .build(true)
                .toUri();

        RestdayDto restdayDto = restTemplate.getForObject(uri, RestdayDto.class);
        if (restdayDto != null
                && restdayDto.getBody() != null
                && restdayDto.getBody().getItems() != null
                && restdayDto.getBody().getItems().getItem() != null
                && !restdayDto.getBody().getItems().getItem().isEmpty()) {
            restdayMapper.insertRestday(restdayDto);
        }

        return restdayDto;
    }


    public int getRestdayCount(String year, String month) {
        RestdayDto restdayDto = getRestdayItems(year, month);

        if (restdayDto != null && restdayDto.getBody() != null) {
            return restdayDto.getBody()
                             .getTotalCount();
        }

        return 0;
    }

}
