package org.example;

import org.example.utils.ReadDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ReadDocumentTest {

    @Test
    void readFile() throws IOException {
        ReadDocument readDocument = new ReadDocument();
        StringBuilder stringBuilder = readDocument.readFile("/authentication-log.json");
        System.out.println(stringBuilder.toString());
    }
}