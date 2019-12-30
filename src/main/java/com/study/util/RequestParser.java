package com.study.util;

import com.study.entity.HttpMethod;
import com.study.entity.HttpStatus;
import com.study.entity.Request;
import com.study.exception.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class RequestParser {

    public static Request parseRequest(BufferedReader reader) {
        try {
            String line = reader.readLine();
            Request request = new Request();
            injectUriAndMethod(request, line);
            injectHeaders(request, reader);
            return request;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ServerException(HttpStatus.METHOD_NOT_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException(HttpStatus.BAD_REQUEST);
        }

    }

    static void injectUriAndMethod(Request request, String requestLine) {
        String[] strings = requestLine.split(" ");
        try {
            request.setHttpMethod(HttpMethod.valueOf(strings[0]));
            request.setUri(strings[1]);
        } catch (IllegalArgumentException ex) {
            System.out.println("injectUriAndMethod: " + ex.getMessage());
            ex.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    static void injectHeaders(Request request, BufferedReader reader) throws IOException { // почему я должна тут
        // указывать throws IOException, хотя я делаю catch для неё?
        try {
            Map <String, String> headersMap = new HashMap<>();
            while (true) {
                String line = reader.readLine();
                if (line.isEmpty()) {
                    break;
                }
                String[] strings = line.split(": ");
                headersMap.put(strings[0], strings[1]);
            }
            request.setHeaders(headersMap);
        } catch (IOException ex) {
            System.out.println("injectHeaders: " + ex.getMessage());
            ex.printStackTrace();
            throw new IOException();
        }
    }
}
