package org.example.logshandling;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.LogEntry;
import org.example.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogsManipulation {
    private ReadDocument readDocument;
    private WriteToDocument writeToDocument;
    public LogsManipulation(ReadDocument readDocument, WriteToDocument writeToDocument) {
        this.readDocument = readDocument;
        this.writeToDocument = writeToDocument;
    }

    // TODO duplicate on WriteToDocument
    private LogEntry correctDocumentLogEntryTimeStamp(LogEntry logEntry){
        int startIndex = logEntry.getTimestamp().indexOf("\"");
        int lastIndex = logEntry.getTimestamp().lastIndexOf("\"");
        logEntry.setTimestamp(logEntry.getTimestamp().substring(startIndex+1, lastIndex));

        return logEntry;
    }

    private List<LogEntry> resetListLogEntry() throws IOException {
        List<LogEntry> logEntries = Parser.jsonArrayToPojo(readDocument.readFile())
                .stream()
                .map(this::correctDocumentLogEntryTimeStamp)
                .map(TimestampManipulation::resetDateTime)
                .collect(Collectors.toList());

        return logEntries;
    }

    public List<LogEntry> addDaysToLogs(List<LogEntry>logEntries, int workDays) throws IOException {
        List<LogEntry> copyListForDays = ListManipulation.createCopyList(logEntries);
        List<LogEntry> logEntriesFinal = new ArrayList<>();

        for (int i = 0; i < workDays; i++) {
            logEntriesFinal = ListManipulation.addTimeList(logEntries, TimestampManipulation
                    .addNormalWorkingDaysToLogList(copyListForDays));
        }
        return logEntriesFinal;
    }

    public List<LogEntry> addHoursToLogs(List<LogEntry>logEntries, int workHours) throws IOException {
        List<LogEntry> copyListForHours = ListManipulation.createCopyList(logEntries);
        List<LogEntry> logEntriesFinal = new ArrayList<>();

        for (int i = 0; i < workHours; i++) {
            logEntriesFinal = ListManipulation.addTimeList(logEntries, TimestampManipulation
                    .addNormalWorkingHoursToLogList(copyListForHours));
        }
        return logEntriesFinal;
    }

    public List<LogEntry> addDateTimeToLogs(int workDays, int workHours) throws IOException {
        List<LogEntry> logDaysEntries = addDaysToLogs(resetListLogEntry(), workDays);
        List<LogEntry> logHoursEntries = addHoursToLogs(logDaysEntries, workHours);
        List<LogEntry> logEntryList = logHoursEntries.stream().sorted((o1, o2) -> o1.compare(o1, o2)).collect(Collectors.toList());

        return logEntryList;
    }

    public void writeStringLogsToFile(String fileName) throws IOException {
        writeToDocument.writeOnResourceFile(addDateTimeToLogs(5, 9), fileName);
    }

    public void writeJsonLogsToFile(String fileName) throws IOException {
        writeToDocument.writeOnResourceFileJson(addDateTimeToLogs(5, 9), fileName);
    }

    public static LogEntry addDayTime(String logEntry, String timeStamp) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        LogEntry newLogEntry = Parser.jsonToPojo(sb.append(logEntry));
        newLogEntry.setTimestamp(timeStamp);
        return newLogEntry;
    }

    public static LogEntry addUser(String logEntry, String userName) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        LogEntry newLogEntry = Parser.jsonToPojo(sb.append(logEntry));
        newLogEntry.getUser().setName(userName);
        return newLogEntry;
    }

    public static LogEntry addHost(String logEntry, String hostName) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        LogEntry newLogEntry = Parser.jsonToPojo(sb.append(logEntry));
        newLogEntry.getHost().setName(hostName);
        return newLogEntry;
    }

    public static LogEntry addSourceIp(String logEntry, String sourceIp) throws JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        LogEntry newLogEntry = Parser.jsonToPojo(sb.append(logEntry));
        newLogEntry.getSource().setIp(sourceIp);
        return newLogEntry;
    }
}
