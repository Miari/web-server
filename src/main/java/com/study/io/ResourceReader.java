package com.study.io;

import com.study.entity.HttpStatus;
import com.study.exception.ServerException;

import java.io.*;
import java.nio.file.Files;

public class ResourceReader {

    /*public static String readContent(String webAppPath, String uri) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(webAppPath + uri)) {
            int count;
            byte[] buffer = new byte[8196];
            while ((count = inputStream.read(buffer)) != -1) {
                byte [] bufferWithoutEmptyElements = Arrays.copyOf(buffer, count);
                String s = Arrays.toString(bufferWithoutEmptyElements);
                //String s = new String(bufferWithoutEmptyElements);
                stringBuilder.append(s);
            }
        } catch (IOException ex) {
            System.out.println("File is not read: " + ex.getMessage());
        }
        return stringBuilder.toString();
    }*/

    public static byte[] readContent(String webAppPath, String uri) {
        try {
            File file = new File(webAppPath + uri);
            return Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            System.out.println("ReadContent method. File could not be found by the path: " + ex.getMessage());
            ex.printStackTrace();
            throw new ServerException(HttpStatus.NOT_FOUND);
        }
    }
}
