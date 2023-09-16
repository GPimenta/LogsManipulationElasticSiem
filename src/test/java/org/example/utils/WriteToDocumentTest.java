package org.example.utils;

import org.example.model.LogEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class WriteToDocumentTest {


    @Test
    void correctDocument() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
//        Parser parser = new Parser(readDocument.readFile());
        WriteToDocument writeToDocument = new WriteToDocument("/logs.txt");

        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile());

        List<LogEntry> logEntryList = logEntries.stream().map(writeToDocument::correctDocumentLogEntryTimeStamp).collect(Collectors.toList());

        logEntryList.forEach(System.out::println);

    }

    @Test
    void writeOnResourceFile() throws IOException {
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
//        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile());
        writeToDocument.writeOnResourceFile(logEntries, "/logs.txt");
    }

//    @Test
//    void writeLogs() throws IOException {
//        WriteToDocument writeToDocument = new WriteToDocument("/logs");
//        writeToDocument.writeLogs();
//
//    }

//    @Test
//    void addHoursToLogs() throws IOException {
//        WriteToDocument writeToDocument = new WriteToDocument("/logs");
//        writeToDocument.addHoursToLogs(writeToDocument.resetListLogEntry()).forEach(System.out::println);
//    }
//
//    @Test
//    void addDaysToLogs() throws IOException {
//        WriteToDocument writeToDocument = new WriteToDocument("/logs");
//        writeToDocument.addDaysToLogs(writeToDocument.resetListLogEntry()).forEach(System.out::println);
//    }

//    @Test
//    void addDateTimeToLogs() throws IOException {
//        WriteToDocument writeToDocument = new WriteToDocument("/logs");
//        List<LogEntry> logEntries = writeToDocument.addDateTimeToLogs();
//        logEntries.stream().sorted((o1, o2) -> o1.compare(o1,o2)).forEach(System.out::println);
////        logEntries.sort(Comparator.comparing(logEntry -> logEntry.getUser().toString()));
////        logEntries.forEach(System.out::println);
//
//    }

}