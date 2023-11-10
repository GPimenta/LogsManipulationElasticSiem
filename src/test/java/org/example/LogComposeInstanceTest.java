package org.example;

import org.example.utils.ReadDocument;
import org.example.utils.WriteToDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogComposeInstanceTest {


    @Test
    public void resetLogsTest() throws IOException {
        ReadDocument readDocumentReset = new ReadDocument("/authentication-log.json");
        WriteToDocument writeToDocumentReset = new WriteToDocument("/logs");
        LogComposeInstance logComposeInstance = new LogComposeInstance(readDocumentReset, writeToDocumentReset);
        logComposeInstance.resetLogs();
    }

    @Test
    public void addLogDayTime() throws IOException {
        ReadDocument readDocumentReset = new ReadDocument("/authentication-log.json");
        WriteToDocument writeToDocumentReset = new WriteToDocument("/logs");
        LogComposeInstance logComposeInstance = new LogComposeInstance(readDocumentReset, writeToDocumentReset);
        logComposeInstance.resetLogs();

        logComposeInstance.addLogDayTime(writeToDocumentReset, "BANANA");
    }

}