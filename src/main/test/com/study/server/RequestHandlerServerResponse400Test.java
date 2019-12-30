package com.study.server;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class RequestHandlerServerResponse400Test {
    @Test
    public void handleTest() throws IOException {
        byte[] b = new byte[26];
        try (
                BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/testData/getRequest400")));
                BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("resources/testData/fileToWriteServerResponse400"));
                FileInputStream inputStream = new FileInputStream("resources/testData/fileToWriteServerResponse400")) {
            RequestHandler requestHandler = new RequestHandler(bufferedReaderFromFile, writer, "resources/testData");
            requestHandler.handle();
            inputStream.read(b);
            assertEquals("HTTP/1.1 400 Bad Request\r\n", new String(b));
        }
    }
}
