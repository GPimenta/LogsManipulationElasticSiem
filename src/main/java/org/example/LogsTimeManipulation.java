package org.example;

import org.example.model.LogEntry;
import org.example.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogsTimeManipulation {
    private ReadDocument readDocument;
    private WriteToDocument writeToDocument;
    public LogsTimeManipulation(ReadDocument readDocument, WriteToDocument writeToDocument) {
        this.readDocument = readDocument;
        this.writeToDocument = writeToDocument;
    }

    private LogEntry correctDocumentLogEntryTimeStamp(LogEntry logEntry){
        int startIndex = logEntry.getTimestamp().indexOf("\"");
        int lastIndex = logEntry.getTimestamp().lastIndexOf("\"");
        logEntry.setTimestamp(logEntry.getTimestamp().substring(startIndex+1, lastIndex));

        return logEntry;
    }

    private List<LogEntry> resetListLogEntry() throws IOException {
        List<LogEntry> logEntries = Parser.jsonToPojo(readDocument.readFile())
                .stream()
                .map(this::correctDocumentLogEntryTimeStamp)
                .map(TimestampConverter::resetDateTime)
                .collect(Collectors.toList());

        return logEntries;
    }

    public List<LogEntry> addDaysToLogs(List<LogEntry>logEntries, int workDays) throws IOException {
        List<LogEntry> copyListForDays = DuplicateList.createCopyList(logEntries);
        List<LogEntry> logEntriesFinal = new ArrayList<>();

        for (int i = 0; i < workDays; i++) {
            logEntriesFinal = DuplicateList.duplicateList(logEntries, TimestampConverter
                    .addDayToLogList(copyListForDays));
        }
        return logEntriesFinal;
    }

    public List<LogEntry> addHoursToLogs(List<LogEntry>logEntries, int workHours) throws IOException {
        List<LogEntry> copyListForHours = DuplicateList.createCopyList(logEntries);
        List<LogEntry> logEntriesFinal = new ArrayList<>();

        for (int i = 0; i < workHours; i++) {
            logEntriesFinal = DuplicateList.duplicateList(logEntries, TimestampConverter
                    .addHourToLogList(copyListForHours));
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


}
