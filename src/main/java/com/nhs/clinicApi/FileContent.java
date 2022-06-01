package com.nhs.clinicApi;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class FileContent {

    public FileContent(String path) {
        this.path = path;
    }
    public String path;
    public List<String> lines = new ArrayList<>();

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(path));

        try {
            reader.readLine(); // NOTE: Skip header;
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            reader.close();
        }
    }
}
