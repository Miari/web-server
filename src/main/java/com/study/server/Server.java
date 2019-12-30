package com.study.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
    private final static int DEFAULT_PORT = 3000;
    private int port;
    private String webAppPAth;

    Server() {
        this(DEFAULT_PORT);
    }

    Server(int port) {
        this.port = port;
    }

    public void start() {
        System.out.println("Start working");
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedOutputStream writer = new BufferedOutputStream(socket.getOutputStream())) {
                    RequestHandler requestHandler = new RequestHandler(reader, writer, webAppPAth);
                    requestHandler.handle();

                } catch (SocketException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPAth(String webAppPAth) {
        this.webAppPAth = webAppPAth;
    }
}
