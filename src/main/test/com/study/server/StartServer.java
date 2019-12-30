package com.study.server;

public class StartServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.setWebAppPAth("src/main/resources/com/study/testData");
        server.start();
    }


}
