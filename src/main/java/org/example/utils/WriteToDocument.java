package org.example.utils;

import org.example.model.LogEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class WriteToDocument {

    private String file;

    public WriteToDocument(String resourceFolder) {
        String basePath = "./src/main/resources";
        this.file = basePath + resourceFolder;
    }

    public LogEntry correctDocumentLogEntryTimeStamp(LogEntry logEntry){
        int startIndex = logEntry.getTimestamp().indexOf("\"");
        int lastIndex = logEntry.getTimestamp().lastIndexOf("\"");
        logEntry.setTimestamp(logEntry.getTimestamp().substring(startIndex+1, lastIndex));

        return logEntry;
    }

    public void writeOnResourceFile(List<LogEntry> logEntries, String fileName) throws IOException {
        try(FileWriter fileWriter = new FileWriter(file + fileName)) {
            for (LogEntry logEntry: logEntries) {
                fileWriter.write(logEntry.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeOnResourceFileJson(List<LogEntry> logEntries, String fileName) {
        try(FileWriter fileWriter = new FileWriter(file + fileName)) {
            for (LogEntry logEntry: logEntries) {
                fileWriter.write(Parser.pojoToJson(logEntry));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

//    public void writeLogs() throws IOException {
//        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
//        Parser parser = new Parser(readDocument.readFile());
//        List<LogEntry> logEntries = parser.jsonToPojo();
//        List<LogEntry> logEntriesCorrected = logEntries.stream().map(this::correctDocumentLogEntryTimeStamp).collect(Collectors.toList());
//        writeOnResourceFile(logEntriesCorrected, "/logs.txt");
//    }
}
