package com.study.server;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class RequestHandlerServerResponse405Test {
    @Test
    public void handleTest() throws IOException {
        byte[] b = new byte[33];
        try (
                BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest405")));
                BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("resources/com/study/testData/fileToWriteServerResponse405"));
                FileInputStream inputStream = new FileInputStream("resources/com/study/testData/fileToWriteServerResponse405")) {
            RequestHandler requestHandler = new RequestHandler(bufferedReaderFromFile, writer, "resources/com/study/testData");
            requestHandler.handle();
            inputStream.read(b);
            assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", new String(b));
        }
    }
}
