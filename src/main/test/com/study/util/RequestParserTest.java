package com.study.util;

import com.study.entity.HttpMethod;
import com.study.entity.Request;
import com.study.exception.ServerException;
import org.junit.Test;

import java.io.*;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class RequestParserTest {

    @Test
    public void injectUriAndMethodTest() {
        Request request = new Request();
        RequestParser.injectUriAndMethod(request, "GET /index.html HTTP/1.1");
        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/index.html", request.getUri());
    }

    @Test(expected = IllegalArgumentException.class)
    public void injectUriAndMethodExceptionTest() {
        Request request = new Request();
        RequestParser.injectUriAndMethod(request, "TRACE /index.html HTTP/1.1");
    }

    @Test
    public void injectHeadersTest() throws IOException {
        Request request = new Request();
        try (BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest")))) {
            bufferedReaderFromFile.readLine();
            RequestParser.injectHeaders(request, bufferedReaderFromFile);
            Map map = request.getHeaders();
            assertEquals("www.w3.org", map.get("Host"));
            assertEquals("text/html", map.get("Accept"));
        }
    }

    @Test(expected = IOException.class)
    public void injectHeadersTestException() throws IOException {
        Request request = new Request();
        BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest")));
        bufferedReaderFromFile.close();
        RequestParser.injectHeaders(request, bufferedReaderFromFile);
    }

    @Test
    public void parseRequestTest() throws IOException {
        try (BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest")))) {
            Request request = RequestParser.parseRequest(bufferedReaderFromFile);
            assertEquals("/index.html", request.getUri());
            assertEquals(HttpMethod.GET, request.getHttpMethod());
            Map map = request.getHeaders();
            assertEquals("www.w3.org", map.get("Host"));
            assertEquals("text/html", map.get("Accept"));
        }
    }

    @Test(expected = ServerException.class)
    public void parseRequestTestMethodNotAllowed() throws FileNotFoundException {
        BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequestWithIncorrectMethod")));
        RequestParser.parseRequest(bufferedReaderFromFile);
    }

    @Test(expected = ServerException.class)
    public void parseRequestTestBadRequest() throws IOException {
        BufferedReader bufferedReaderFromFile = new BufferedReader(new InputStreamReader(new FileInputStream("resources/com/study/testData/getRequest")));
        bufferedReaderFromFile.close();
        RequestParser.parseRequest(bufferedReaderFromFile);
    }
}
