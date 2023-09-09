package org.example.utils;

import org.example.utils.ReadDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ReadDocumentTest {

    @Test
    void readFile() throws IOException {
        ReadDocument readDocument = new ReadDocument("/authentication-log.json");
        StringBuilder stringBuilder = readDocument.readFile();
        System.out.println(stringBuilder.toString());
    }
}