package com.study.server;

import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class RequestHandlerServerResponse200Test {

    @Test
    public void handleTest() throws IOException {
        byte[] b = new byte[61];
        try (
                BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/testData/getRequest")));
                BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("resources/testData/fileToWriteServerResponse200"));
                FileInputStream inputStream = new FileInputStream("resources/testData/fileToWriteServerResponse200")) {
            RequestHandler requestHandler = new RequestHandler(bufferedReaderFromFile, writer, "resources/testData");
            requestHandler.handle();
            inputStream.read(b);
            assertEquals("HTTP/1.1 200 OK\r\n\r\n<!doctype html>\r\n<html lang=\"en\">\r\n</html>", new String(b));
        }
    }
}
