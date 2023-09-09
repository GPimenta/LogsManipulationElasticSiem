package org.example.utils;

import org.example.model.LogEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToDocument {

    private String file;

    public WriteToDocument(String resourceFolder) {
        String basePath = "./src/main/resources";
        this.file = basePath + resourceFolder;
    }


    public LogEntry correctDocumentLogEntry(LogEntry logEntry){
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

        }catch (IOException e) {
            System.out.println(e);
        }
    }

    public void writeLogs() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        Parser parser = new Parser(readDocument.readFile());
        List<LogEntry> logEntries = parser.jsonToPojo();
        writeOnResourceFile(logEntries, "/logs.txt");
    }
}
