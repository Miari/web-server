package com.study.server;

import com.study.entity.HttpStatus;
import com.study.entity.Request;
import com.study.exception.ServerException;
import com.study.io.ResourceReader;
import com.study.util.RequestParser;
import com.study.util.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedOutputStream writer;
    private String webAppPath; //resources

    public RequestHandler(BufferedReader reader, BufferedOutputStream writer, String webAppPath) {
        if (reader == null || writer == null || webAppPath == null) {
            throw new NullPointerException("Reader, writter and webAppPath cannot be null");
        }
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        ResponseWriter responseWriter = new ResponseWriter(writer);
        try {
            Request request = RequestParser.parseRequest(reader);
            //String content = ResourceReader.readContent(webAppPath, request.getUri());
            byte[] b = ResourceReader.readContent(webAppPath, request.getUri());
            //responseWriter.writeResponse(HttpStatus.OK, content);
            responseWriter.writeResponse(HttpStatus.OK, b);
        } catch (ServerException e) {
            e.printStackTrace();
            responseWriter.writeResponse(e.getHttpStatus(), null);
        } catch (Exception e) {
            e.printStackTrace();
            responseWriter.writeResponse(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
        System.out.println("Finish");
    }
}
