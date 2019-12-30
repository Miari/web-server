package com.study.server;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;

public class TestServerResponse404 {


    private final static String LINE_END = "\r\n";

    @Test //to run only after StartServerClass
    public void checkRequestHandlerServerResponse404() throws IOException {
        try (Socket socketToServer = new Socket("localhost", 3000);
             BufferedReader bufferedReaderFromServer = new BufferedReader(new InputStreamReader(socketToServer.getInputStream()));
             BufferedWriter bufferedWriterToServer = new BufferedWriter(new OutputStreamWriter(socketToServer.getOutputStream()))) {
            bufferedWriterToServer.write("GET /index1.html HTTP/1.1");
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.write(LINE_END);
            bufferedWriterToServer.flush();

            assertEquals("HTTP/1.1 404 Not Found", bufferedReaderFromServer.readLine());
        }
    }

}
