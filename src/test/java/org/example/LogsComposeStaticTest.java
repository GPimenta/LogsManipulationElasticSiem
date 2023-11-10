package org.example;

import org.example.utils.ReadDocument;
import org.example.utils.WriteToDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class LogsComposeStaticTest {

    @Test
    void resetLogsTest() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsComposeStatic.resetLogs(readDocument, writeToDocument);
    }

    @Test
    void addLogDayTimeTest() throws IOException {
        String time = "2024-08-15T09:00:00";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsComposeStatic.addLogDayTime(writeToDocument, time);

    }

    @Test
    void addLogUserTest() throws IOException {
        String userName = "Banana";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsComposeStatic.addLogUser(writeToDocument, userName);
    }

    @Test
    void addHostTest() throws IOException {
        String host = "ServerCircus";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsComposeStatic.addHost(writeToDocument, host);
    }

    @Test
    void addSourceIpTest() throws IOException {
        String sourceIp = "127.0.0.1";
        WriteToDocument writeToDocument = new WriteToDocument("/logs");
        LogsComposeStatic.addSourceIp(writeToDocument, sourceIp);
    }
}