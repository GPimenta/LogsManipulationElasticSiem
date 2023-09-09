package org.example.model;

import lombok.Data;

import java.util.List;

@Data
public class LogEntryWrapper {
    private List<LogEntry> logEntries;

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }
}
