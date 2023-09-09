package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.model.LogEntryWrapper;
import org.example.model.LogEntry;

@Data
public class Parser {

    public LogEntryWrapper jsonToPojo(StringBuilder jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        LogEntryWrapper logEntryWrapper = objectMapper.readValue(jsonString.toString(), LogEntryWrapper.class);
        return logEntryWrapper;
    }
}
