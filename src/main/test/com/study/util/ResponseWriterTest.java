package com.study.util;

import com.study.entity.HttpStatus;
import org.junit.Test;

import java.io.*;

import static junit.framework.TestCase.assertEquals;

public class ResponseWriterTest {
    @Test
    public void writeResponseTest() throws IOException {
        byte[] b = new byte[61];
        try (
                BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("resources/com/study/testData/fileToWrite"));
                FileInputStream inputStream = new FileInputStream("resources/com/study/testData/fileToWrite")) {
            ResponseWriter responseWriter = new ResponseWriter(writer);
            byte[] arrayToWrite = {60, 33, 100, 111, 99, 116, 121, 112, 101, 32, 104, 116, 109, 108, 62, 13, 10, 60,
                    104, 116, 109, 108, 32, 108, 97, 110, 103, 61, 34, 101, 110, 34, 62, 13, 10, 60, 47, 104, 116, 109,
                    108, 62};
            responseWriter.writeResponse(HttpStatus.OK, arrayToWrite);
            writer.flush();
            inputStream.read(b);
        }
        assertEquals("HTTP/1.1 200 OK\r\n\r\n<!doctype html>\r\n<html lang=\"en\">\r\n</html>", new String(b));

    }

    /*@Test (expected = IOException.class)
    public void writeResponseTestException() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("resources/com/study/testData/fileToWrite");
        BufferedOutputStream writer = new BufferedOutputStream(fileOutputStream);
        fileOutputStream.close();
        ResponseWriter responseWriter = new ResponseWriter(writer); // почему responseWriter создается (и в дальнейшем в
         него происходит записть), котя поток, который оборачивает BufferedOutputStream, закрыт?

        byte[] arrayToWrite = {60, 33, 100, 111, 99, 116, 121, 112, 101, 32, 104, 116, 109, 108, 62, 13, 10, 60,
        104, 116, 109, 108, 32, 108, 97, 110, 103, 61, 34, 101, 110, 34, 62, 13, 10, 60, 47, 104, 116, 109, 108, 62};
        responseWriter.writeResponse(HttpStatus.OK, arrayToWrite);
    }*/
}
