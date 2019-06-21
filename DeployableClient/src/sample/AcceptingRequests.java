package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptingRequests implements Runnable {
    ServerSocket server;
    Socket socket;
    public static boolean flag=false;
    @Override
    public void run() {
        try {
            server = new ServerSocket(9698);
            socket = server.accept();
            flag=true;
            Functions.disconnect();
            Client.socket = socket;
            Client.oos = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.ois = new ObjectInputStream(Client.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
