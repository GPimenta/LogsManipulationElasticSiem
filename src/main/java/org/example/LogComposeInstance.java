package org.example;

import org.example.logshandling.LogsManipulation;
import org.example.model.LogEntry;
import org.example.utils.Parser;
import org.example.utils.ReadDocument;
import org.example.utils.WriteToDocument;

import java.io.IOException;
import java.util.List;

public class LogComposeInstance {

    public ReadDocument readDocument;
    public WriteToDocument writeToDocument;

    public LogComposeInstance(ReadDocument readDocument, WriteToDocument writeToDocument) {
        this.readDocument = readDocument;
        this.writeToDocument = writeToDocument;
    }

    public void resetLogs() throws IOException {
        ReadDocument readDocumentReset = new ReadDocument("/authentication-log.json");
        WriteToDocument writeToDocumentReset = new WriteToDocument("/logs");
        LogsManipulation logsManipulation = new LogsManipulation(readDocumentReset, writeToDocumentReset);
        logsManipulation.writeJsonLogsToFile("/logs.json");
    }

    public void addLogDayTime(WriteToDocument writeToDocument, String userName) throws IOException {
        ReadDocument readDocumentFresh = new ReadDocument("/logs/logs.json");
        List<LogEntry> logEntries = Parser.jsonListToPojo(readDocumentFresh.readFile());
        ReadDocument readDocumentOneLog = new ReadDocument("/one-log.json");
        LogEntry logEntry = LogsManipulation.addUser(readDocumentOneLog.readFile().toString(), userName);
        logEntries.add(logEntry);

        writeToDocument.writeOnResourceFileJson(logEntries, "/jsonUser.json");
    }

}
