package com.nhs.clinicApi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ClinicApiApplicationTests {

    @Test
    void contextLoads() {
        assert  1 == 1;
    }

    @Test
    void testFileContentLoadsContent() {
        FileContent doctorsFileContent = new FileContent("lekarze.txt");
        FileContent patientFileContent= new FileContent("pacjenci.txt");

        try {
            doctorsFileContent.read();
            patientFileContent.read();
        } catch(IOException err) {

        }

        assert doctorsFileContent.lines.size() > 0;
        assert patientFileContent.lines.size() > 0;
    }

}
