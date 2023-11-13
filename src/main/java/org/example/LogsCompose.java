package org.example;

import org.example.logshandling.LogsManipulation;
import org.example.model.LogEntry;
import org.example.utils.Parser;
import org.example.utils.ReadDocument;
import org.example.utils.WriteToDocument;

import java.io.IOException;
import java.util.List;

public class LogsCompose {
    static {
        ReadDocument readDocument = new ReadDocument("src/main/resources/authentication-log.json");
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);
        try {
            logsManipulation.writeJsonLogsToFile("/logs.json");
        } catch (IOException e) {
            throw new RuntimeException("STATIC" + e);
        }
    }

    public static void resetLogs(ReadDocument readDocument, WriteToDocument writeToDocument) throws IOException {
        LogsManipulation logsManipulation = new LogsManipulation(readDocument, writeToDocument);
        logsManipulation.writeJsonLogsToFile("/logs.json");
    }

    public static void addLogDayTime(WriteToDocument writeToDocument, String time) throws IOException {
        ReadDocument readDocumentFresh = new ReadDocument("src/main/resources/logs/logs.json");
        List<LogEntry> logEntries = Parser.jsonListToPojo(readDocumentFresh.readFile());

        ReadDocument readDocumentOneLog = new ReadDocument("src/main/resources/one-log.json");
        LogEntry logEntry = LogsManipulation.addDayTime(readDocumentOneLog.readFile().toString(), time);
        logEntries.add(logEntry);

        writeToDocument.writeOnResourceFileJson(logEntries, "/jsonDayTime.json");
    }

    public static void addLogUser(WriteToDocument writeToDocument, String userName) throws IOException {
        ReadDocument readDocumentFresh = new ReadDocument("src/main/resources/logs/logs.json");
        List<LogEntry> logEntries = Parser.jsonListToPojo(readDocumentFresh.readFile());

        ReadDocument readDocumentOneLog = new ReadDocument("src/main/resources/one-log.json");
        LogEntry logEntry = LogsManipulation.addUser(readDocumentOneLog.readFile().toString(), userName);
        logEntries.add(logEntry);

        writeToDocument.writeOnResourceFileJson(logEntries, "/jsonUser.json");
    }

    public static void addHost(WriteToDocument writeToDocument, String host) throws IOException {
        ReadDocument readDocumentFresh = new ReadDocument("src/main/resources/logs/logs.json");
        List<LogEntry> logEntries = Parser.jsonListToPojo(readDocumentFresh.readFile());

        ReadDocument readDocumentOneLog = new ReadDocument("src/main/resources/one-log.json");
        LogEntry logEntry = LogsManipulation.addHost(readDocumentOneLog.readFile().toString(), host);
        logEntries.add(logEntry);

        writeToDocument.writeOnResourceFileJson(logEntries, "/jsonHost.json");
    }

    public static void addSourceIp(WriteToDocument writeToDocument, String sourceIp) throws IOException {
        ReadDocument readDocumentFresh = new ReadDocument("src/main/resources/logs/logs.json");
        List<LogEntry> logEntries = Parser.jsonListToPojo(readDocumentFresh.readFile());

        ReadDocument readDocumentOneLog = new ReadDocument("src/main/resources/one-log.json");
        LogEntry logEntry = LogsManipulation.addSourceIp(readDocumentOneLog.readFile().toString(), sourceIp);
        logEntries.add(logEntry);

        writeToDocument.writeOnResourceFileJson(logEntries, "/jsonSourceIp.json");
    }
}
