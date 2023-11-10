package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.model.LogEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Parser {

    public static List<LogEntry> jsonArrayToPojo(StringBuilder jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, LogEntry.class));
    }

    public static List<LogEntry> jsonListToPojo(StringBuilder jsonString) throws JsonProcessingException {
        List<LogEntry> logEntries = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String[] lines = jsonString.toString().split("\n");

        for (String line: lines) {
            LogEntry logEntry = objectMapper.readValue(line, LogEntry.class);
            logEntries.add(logEntry);
        }
        return logEntries;
    }


    public static LogEntry jsonToPojo (StringBuilder jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString.toString(), LogEntry.class);
    }

    public static String pojoToJson(LogEntry logEntry) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(logEntry) + "\n";
    }
}
