package com.study.io;

import com.study.exception.ServerException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ResourceReaderTest {

    /*@StartServer
    public void injectUriAndMethodTest() {
        String content = ResourceReader.readContent("resources/testData", "/index.html");
        assertEquals("<!doctype html>\r\n<html lang=\"en\">\r\n</html>", content);
    }

    @StartServer
    public void injectUriAndMethodTestException() {
        String content = ResourceReader.readContent("resources/testData", "/index1.html");
        assertEquals("", content);
    }*/

    @Test
    public void injectUriAndMethodTest() {
        byte[] byteArray = ResourceReader.readContent("resources/testData", "/index.html");
        byte[] arrayToCompare = {60, 33, 100, 111, 99, 116, 121, 112, 101, 32, 104, 116, 109, 108, 62, 13, 10, 60, 104, 116, 109, 108, 32, 108, 97, 110, 103, 61, 34, 101, 110, 34, 62, 13, 10, 60, 47, 104, 116, 109, 108, 62};
        for (int i = 0; i < byteArray.length; i++) {
            assertEquals(byteArray[i], arrayToCompare[i]);
        }
    }

    @Test(expected = ServerException.class)
    public void injectUriAndMethodExceptionTest() {
        ResourceReader.readContent("resources/testData", "/index1.html");
    }
}
