package org.example.utils;

import java.io.*;

public class ReadDocument {

    private String resourceFile;

    public ReadDocument(String resourceFile){
        this.resourceFile = resourceFile;
    }

    public StringBuilder readFile() throws IOException {
        StringBuilder jsonString = new StringBuilder();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.resourceFile))) {

            while ((line = reader.readLine()) != null) {
                jsonString.append(line).append("\n");
            }
        }
        return jsonString;
    }
}
