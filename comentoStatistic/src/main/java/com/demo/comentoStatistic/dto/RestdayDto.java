package com.demo.comentoStatistic.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JacksonXmlRootElement(localName = "response")
public class RestdayDto {
    private Header header;
    private Body body;

    @Getter
    @Setter
    public static class Header {
        private String resultCode;
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        private Items items;
        private int totalCount;
        private int pageNo;
        private int numOfRows;
    }

    @Getter
    @Setter
    public static class Items {
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Item> item = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class Item {
        private int locdate;
        private String dateName;
        private String isHoliday;
        private int seq;
    }
}

