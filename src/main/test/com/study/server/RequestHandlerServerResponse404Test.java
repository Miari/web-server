package com.study.server;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class RequestHandlerServerResponse404Test {
    @Test
    public void handleTest() throws IOException {
        byte[] b = new byte[24];
        try (
                BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest404")));
                BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("resources/com/study/testData/fileToWriteServerResponse404"));
                FileInputStream inputStream = new FileInputStream("resources/com/study/testData/fileToWriteServerResponse404")) {
            RequestHandler requestHandler = new RequestHandler(bufferedReaderFromFile, writer, "resources/com/study/testData");
            requestHandler.handle();
            inputStream.read(b);
            assertEquals("HTTP/1.1 404 Not Found\r\n", new String(b));
        }
    }
}
