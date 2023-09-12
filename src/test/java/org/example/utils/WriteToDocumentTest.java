package org.example.utils;

import org.example.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class WriteToDocumentTest {


    @Test
    void correctDocument() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        Parser parser = new Parser(readDocument.readFile());
        WriteToDocument writeToDocument = new WriteToDocument("/logs.txt");

        List<LogEntry> logEntries = parser.jsonToPojo();

        List<LogEntry> logEntryList = logEntries.stream().map(writeToDocument::correctDocumentLogEntryTimeStamp).collect(Collectors.toList());

        logEntryList.forEach(System.out::println);

    }

    @Test
    void writeOnResourceFile() throws IOException {
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = parser.jsonToPojo();
        writeToDocument.writeOnResourceFile(logEntries, "/logs.txt");
    }

    @Test
    void writeLogs() throws IOException {
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        writeToDocument.writeLogs();

    }

    @Test
    void addTime() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = parser.jsonToPojo();
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        List<LogEntry> logEntriesCorrected = logEntries.stream().map(writeToDocument::correctDocumentLogEntryTimeStamp).collect(Collectors.toList());

        writeToDocument.addTime();
    }

    @Test
    void addTimeLogs() throws IOException {
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        writeToDocument.addTimeLogs().forEach(System.out::println);
    }

}