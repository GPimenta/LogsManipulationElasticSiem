package org.example;

import org.example.logshandling.LogsManipulation;
import org.example.model.LogEntry;
import org.example.utils.Parser;
import org.example.utils.ReadDocument;
import org.example.utils.WriteToDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogsComposeTest {

    @Test
    void resetLogsTest() throws IOException {
        ReadDocument readDocument = new ReadDocument("src/main/resources/authentication-log.json");
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsCompose.resetLogs(readDocument, writeToDocument);
    }

    @Test
    void addLogDayTimeTest() throws IOException {
        String time = "2023-08-15T23:00:00";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsCompose.addLogDayTime(writeToDocument, time);

    }

    @Test
    void addLogUserTest() throws IOException {
        String userName = "Banana";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsCompose.addLogUser(writeToDocument, userName);
    }

    @Test
    void addHostTest() throws IOException {
        String host = "ServerCircus";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsCompose.addHost(writeToDocument, host);
    }

    @Test
    void addSourceIpTest() throws IOException {
        String sourceIp = "127.0.0.1";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsCompose.addSourceIp(writeToDocument, sourceIp);
    }
}