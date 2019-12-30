package com.study.util;

import com.study.entity.HttpStatus;

import java.io.*;
import java.nio.charset.Charset;

public class ResponseWriter {
    private BufferedOutputStream writer;
    private final static byte[] LINE_END = "\r\n".getBytes();

    public ResponseWriter(BufferedOutputStream writer) {
        this.writer = writer;
    }

    /*public void writeResponse(HttpStatus httpStatus, String body) {
        try {
            writer.write(httpStatus.getStatusLine().getBytes());
            writer.write(LINE_END);
            writer.write(LINE_END);
            byte[] b = body.getBytes();
            writer.write(b);
        } catch (IOException ex) {
            System.out.println("File is not written: " + ex.getMessage());
        }
    }*/

    public void writeResponse(HttpStatus httpStatus, byte[] body) throws IOException{
        try {
            writer.write(httpStatus.getStatusLine().getBytes());
            writer.write(LINE_END);
            if (httpStatus.equals(HttpStatus.OK)) {
                writer.write(LINE_END);
                writer.write(body);
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println("File is not written: " + ex.getMessage());
            throw new IOException();
        }
    }

}
