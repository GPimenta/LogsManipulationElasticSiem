package org.example;

import org.example.model.LogEntry;
import org.example.utils.Parser;
import org.example.utils.ReadDocument;
import org.example.utils.TimestampConverter;
import org.example.utils.WriteToDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class LogsManipulationTest {

    ReadDocument readDocument;
    WriteToDocument writeToDocument;

//    @BeforeAll
//    static void before(){
//       readDocument = new ReadDocument("/authentication-log.json");
//       writeToDocument = new WriteToDocument("/logs");
//    }

    @Test
    void correctDocumentLogEntryTimeStamp() throws IOException {
        readDocument = new ReadDocument("/authentication-log.json");
//        Parser parser = new Parser(readDocument.readFile());

        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile()).stream()
                .map(logEntry ->
                {
                    int startIndex = logEntry.getTimestamp().indexOf("\"");
                    int lastIndex = logEntry.getTimestamp().lastIndexOf("\"");
                    logEntry.setTimestamp(logEntry.getTimestamp().substring(startIndex + 1, lastIndex));
                    return logEntry;
                })
                .collect(Collectors.toList());

        logEntries.forEach(System.out::println);
    }

    @Test
    void resetListLogEntry() throws IOException {
        readDocument = new ReadDocument("/authentication-test-log.json");
//        Parser parser = new Parser(readDocument.readFile());

        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile()).stream()
                .map(TimestampConverter::resetDateTime)
                .collect(Collectors.toList());

        logEntries.forEach(System.out::println);
    }



    @Test
    void addDaysToLogs() throws IOException {
        readDocument = new ReadDocument("/authentication-test-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);

//        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile()).stream()
                .map(TimestampConverter::resetDateTime)
                .collect(Collectors.toList());

        List<LogEntry> logEntriesAddedDays = logsManipulation.addDaysToLogs(logEntries, 5);
        logEntriesAddedDays.forEach(System.out::println);

    }

    @Test
    void addHoursToLogs() throws IOException {
        readDocument = new ReadDocument("/authentication-test-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);

//        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile()).stream()
                .map(TimestampConverter::resetDateTime)
                .collect(Collectors.toList());

        List<LogEntry> logEntriesAddedDays = logsManipulation.addHoursToLogs(logEntries, 9);
        logEntriesAddedDays.forEach(System.out::println);
    }

    @Test
    void addDateTimeToLogs() throws IOException {
        readDocument = new ReadDocument("/authentication-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);

        List<LogEntry> logEntries = logsManipulation.addDateTimeToLogs(5, 9);
        logEntries.forEach(System.out::println);

    }

    @Test
    void writeLogs() throws IOException {
        readDocument = new ReadDocument("/authentication-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);
        logsManipulation.writeStringLogsToFile("/logs.txt");
    }

    @Test
    void writeLogsJson() throws IOException {
        readDocument = new ReadDocument("/authentication-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);
        logsManipulation.writeJsonLogsToFile("/logs.json");
    }

}