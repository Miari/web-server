package com.study.server;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;

public class TestServerResponse200 {

    private final static String LINE_END = "\r\n";

    @Test //to run only after StartServerClass
    public void checkRequestHandlerServerResponse200() throws IOException {
        try (Socket socketToServer = new Socket("localhost", 3000);
             BufferedReader bufferedReaderFromServer = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
             BufferedWriter bufferedWriterToServer = new BufferedWriter(new OutputStreamWriter(socketToServer.getOutputStream()))) {
            bufferedWriterToServer.write("GET /index.html HTTP/1.1");
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.write("Host: www.w3.org");
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.write("Accept: text/html");
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.flush();

            assertEquals("HTTP/1.1 200 OK", bufferedReaderFromServer.readLine());
            assertEquals("", bufferedReaderFromServer.readLine());
            assertEquals("<!doctype html>", bufferedReaderFromServer.readLine());
            assertEquals("<html lang=\"en\">", bufferedReaderFromServer.readLine());
            assertEquals("</html>", bufferedReaderFromServer.readLine());
        }
    }
}
