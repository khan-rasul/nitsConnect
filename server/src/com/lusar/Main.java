package com.lusar;

import javafx.util.Pair;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Main {

    public static Vector<Pair<String,InetAddress>> table = new Vector<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Server Started...");
        ServerSocket server = new ServerSocket(8888);
        while(true)
        {
            Socket socket = server.accept();
            System.out.println("Connected to client...");
            Thread client = new Thread( new ClientThread(socket));
            client.start();
        }
    }
}
