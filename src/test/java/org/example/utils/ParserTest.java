package org.example.utils;

import org.example.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class ParserTest {

    @Test
    void jsonToPojo() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");

//        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile());
        logEntries.forEach(System.out::println);

    }
}