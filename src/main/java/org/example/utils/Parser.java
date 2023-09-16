package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.model.LogEntryWrapper;
import org.example.model.LogEntry;

import java.util.List;

@Data
public class Parser {

//    private StringBuilder jsonString;
//    public Parser(StringBuilder jsonString) {
//        this.jsonString = jsonString;
//    }
//
//    public List<LogEntry> jsonToPojo() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(jsonString.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, LogEntry.class));
//    }

    public static List<LogEntry> jsonToPojo(StringBuilder jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, LogEntry.class));
    }

    public static String pojoToJson(LogEntry logEntry) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(logEntry) + "\n";
    }
}
