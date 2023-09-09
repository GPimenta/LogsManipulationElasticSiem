package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadDocument {

    public StringBuilder readFile(String resourceFile) throws IOException {
        InputStream resourceAsStream = ReadDocument.class.getResourceAsStream(resourceFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
        StringBuilder jsonString = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null ) {
            jsonString.append(line);
        }

        return jsonString;
    }

}