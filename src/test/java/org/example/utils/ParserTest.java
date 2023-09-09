package org.example.utils;

import org.example.model.LogEntryWrapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ParserTest {

    @Test
    void jsonToPojo() throws IOException {
        ReadDocument readDocument = new ReadDocument();

        Parser parser = new Parser();
        LogEntryWrapper logEntries = parser.jsonToPojo(readDocument.readFile("/authentication-log.json"));
        logEntries.getLogEntries().forEach(System.out::println);
    }
}