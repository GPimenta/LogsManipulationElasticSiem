package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimestampManipulationTest {

    @Test
    public void randomHourTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String timestamp = "2023-04-01T19:14:08";
        LocalDateTime result = LocalDateTime.parse(timestamp, formatter);

        LocalDateTime dateTime = TimestampManipulation.randomHour(result);
        System.out.println(dateTime);
    }


    @Test
    public void sumWorkDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        String timestamp = "2023-11-17T19:14:08";
        LocalDateTime result = LocalDateTime.parse(timestamp, formatter);

        LocalDateTime dateTime = TimestampManipulation.sumWorkDays(result);
        System.out.println(dateTime);
    }
}