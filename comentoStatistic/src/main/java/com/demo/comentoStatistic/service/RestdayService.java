package com.demo.comentoStatistic.service;

import com.demo.comentoStatistic.config.RestdayApiConfig;
import com.demo.comentoStatistic.dto.RestdayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class RestdayService {

    private final RestTemplate restTemplate;
    private final RestdayApiConfig restdayApiConfig;

    public RestdayDto getRestdayItems(String year, String month) {
        URI uri = UriComponentsBuilder
                .fromUriString(restdayApiConfig.getUrl()+"/getHoliDeInfo")
                .queryParam("solYear", year)
                .queryParam("solMonth", month)
                .queryParam("ServiceKey", restdayApiConfig.getKey())
                .build(true)
                .toUri();

        return restTemplate.getForObject(uri, RestdayDto.class);
    }

    public int getRestdayCount(String year, String month) {
        RestdayDto restdayDto = getRestdayItems(year, month);
        if (restdayDto != null && restdayDto.getBody() != null) {
            return restdayDto.getBody().getTotalCount();
        }

        return 0;
    }

}
