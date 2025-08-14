package com.demo.comentoStatistic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvalidDateException extends RuntimeException {
    private Set<String> fields;
    private List<String> messages;

}