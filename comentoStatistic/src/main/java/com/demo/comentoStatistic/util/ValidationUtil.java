package com.demo.comentoStatistic.util;

import com.demo.comentoStatistic.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ValidationUtil {

    public static void validateDate(String year, String month, String day) {
        Set<String> errorFields = new LinkedHashSet<>();
        List<String> errorMessages = new ArrayList<>();

        if (year != null) {
            if (!year.matches("20\\d{2}")) {
                errorFields.add("year");
                errorMessages.add("2000년대만 가능합니다.");
            }
        } else if (month != null || day != null) {
            errorMessages.add("year 값이 필요합니다.");
        }

        if (month != null) {
            try {
                int m = Integer.parseInt(month);
                if (m < 1 || m > 12) {
                    errorFields.add("month");
                    errorMessages.add("1월에서 12월 사이 값만 가능합니다.");
                }
            } catch (NumberFormatException e) {
                errorFields.add("month");
                errorMessages.add("숫자 형식이어야 합니다.");
            }
        } else if (day != null) {
            errorMessages.add("month 값이 필요합니다.");
        }

        if (day != null) {
            try {
                int d = Integer.parseInt(day);
                if (d < 1 || d > 31) {
                    errorFields.add("day");
                    errorMessages.add("1일에서 31일 사이 값만 가능합니다.");
                }
            } catch (NumberFormatException e) {
                errorFields.add("day");
                errorMessages.add("숫자 형식이어야 합니다.");
            }
        }

        if (!errorFields.isEmpty() || !errorMessages.isEmpty()) {
            throw new InvalidDateException(errorFields, errorMessages);
        }
    }

    public static void validDateString(String date, String field) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException ex) {
            throw new InvalidDateException(Set.of(field), List.of("YYYYMMDD 형식만 가능합니다."));
        }
    }
}
