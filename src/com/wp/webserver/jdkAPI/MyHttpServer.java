package com.wp.webserver.jdkAPI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MyHttpServer {

    public static void main(String[] args) {
        try {
            HttpServer hs = HttpServer.create(new InetSocketAddress(7777), 0);
            hs.createContext("/myrequest", new MyHandler());
            hs.setExecutor(null);
            hs.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            System.out.println(t.getRequestURI().toString());
            InputStream is = t.getRequestBody();
            byte[] temp = new byte[is.available()];
            is.read(temp);
            System.out.println(temp.length);
            System.out.println(new String(temp));
            String response = "<h3>Hello World!</h3>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}