package com.study.server;

public class StartServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.setWebAppPAth("src/main/resources/testData");
        server.start();
    }


}
