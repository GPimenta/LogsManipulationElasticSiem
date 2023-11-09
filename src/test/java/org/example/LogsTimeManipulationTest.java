package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.logshandling.LogsManipulation;
import org.example.model.LogEntry;
import org.example.utils.Parser;
import org.example.utils.ReadDocument;
import org.example.utils.TimestampManipulation;
import org.example.utils.WriteToDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class LogsTimeManipulationTest {

    ReadDocument readDocument;
    WriteToDocument writeToDocument;

    String logExample = "{\"winlog\":{\"event_data\":{\"LogonType\":\"interactive\"}},\"event\":{\"type\":\"User Login Change Event\",\"original\":\"NA\",\"outcome\":\"success\",\"category\":\"authentication\",\"action\":\"User Login Information\"},\"@timestamp\":\"2023-08-15T09:00:00\",\"user\":{\"name\":\"cbrignall7\"},\"host\":{\"name\":\"server3\"},\"source\":{\"ip\":\"99.7.121.32\"}}\n";

    @Test
    void correctDocumentLogEntryTimeStamp() throws IOException {
        readDocument = new ReadDocument("/authentication-log.json");
//        Parser parser = new Parser(readDocument.readFile());

        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile()).stream()
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

        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile()).stream()
                .map(TimestampManipulation::resetDateTime)
                .collect(Collectors.toList());

        logEntries.forEach(System.out::println);
    }



    @Test
    void addDaysToLogs() throws IOException {
        readDocument = new ReadDocument("/authentication-test-log.json");
        writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);

//        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile()).stream()
                .map(TimestampManipulation::resetDateTime)
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
        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile()).stream()
                .map(TimestampManipulation::resetDateTime)
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

    @Test
    void addDayTimeTest() throws IOException {
        readDocument = new ReadDocument("/one-log.json");
        String time = "2024-08-15T09:00:00";
        LogEntry logEntry = LogsManipulation.addDayTime(readDocument.readFile().toString(), time);
        System.out.println(logEntry);
    }

    @Test
    void addUserTest() throws IOException {
        readDocument = new ReadDocument("/one-log.json");
        String user = "Lolipop";
        LogEntry logEntry = LogsManipulation.addUser(readDocument.readFile().toString(), user);
        System.out.println(logEntry);

    }

    @Test
    void addHostTest() throws IOException {
        readDocument = new ReadDocument("/one-log.json");
        String hostName = "serverBanana";
        LogEntry logEntry = LogsManipulation.addHost(readDocument.readFile().toString(), hostName);
        System.out.println(logEntry);
    }

    @Test
    void addSourceIpTest() throws IOException {
        readDocument = new ReadDocument("/one-log.json");
        String sourceIp = "127.0.0.1";
        LogEntry logEntry = LogsManipulation.addSourceIp(readDocument.readFile().toString(), sourceIp);
        System.out.println(logEntry);
    }
}