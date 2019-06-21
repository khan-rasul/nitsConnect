package sample;

import javafx.util.Pair;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class Client{
    public static Socket socket;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
    public static boolean exit = true;
    public static String clientName ;
    public static String serverMessage;
    public static Vector<Pair<String, InetAddress>> table;
    public static void run() {
        try {
            socket = new Socket("192.168.43.153", 8888);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(clientName);
            serverMessage = "Connected to server";
            Thread accept = new Thread(new AcceptingRequests());
            accept.start();
        }
        catch (IOException e)
        {
            serverMessage = "Couldn't connect to server";
        }
    }
}
