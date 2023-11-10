package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadDocument {

    private String resourceFile;

    public ReadDocument(String resourceFile){
        this.resourceFile = resourceFile;
    }

    public StringBuilder readFile() throws IOException {
        InputStream resourceAsStream = ReadDocument.class.getResourceAsStream(resourceFile);
        StringBuilder jsonString = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {

            while ((line = reader.readLine()) != null) {
                jsonString.append(line).append("\n");
            }
        }
        return jsonString;
    }
}
