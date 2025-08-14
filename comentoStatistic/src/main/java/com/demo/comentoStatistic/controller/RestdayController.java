package com.demo.comentoStatistic.controller;

import com.demo.comentoStatistic.dto.RestdayDto;
import com.demo.comentoStatistic.service.RestdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restday")
public class RestdayController {

    @Autowired
    private RestdayService restdayService;

    @GetMapping
    public int getRestdayCount(@RequestParam String year,  @RequestParam String month) {
        return restdayService.getRestdayCount(year, month);
    }

    @GetMapping("items")
    public RestdayDto getRestdayItems(@RequestParam String year,  @RequestParam String month) {
        return restdayService.getRestdayItems(year, month);
    }

}
