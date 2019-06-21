package sample;

import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class Functions {
    public static void readName( String name)
    {
        Client.clientName = name;
    }
    public static String serverConfirmation()
    {
        return Client.serverMessage;
    }
    public static Vector<Pair<String, InetAddress>> readTable() throws IOException, ClassNotFoundException {
        if(Client.oos!=null)
            Client.oos.writeObject("table");

        if(Client.ois!=null)
            Client.table = ( Vector < Pair < String, InetAddress > > ) Client.ois.readObject();

        return Client.table;
    }
    public static void disconnect(){
        try {
            Client.oos.writeObject("disconnect");
            Client.ois.close();
            Client.oos.close();
            Client.socket.close();

        }
        catch (IOException e)
        {
            System.out.println("Couldn't disconnect");
            return;
        }
    }
    public static void establishConnection( String name) throws IOException {
        for( int i = 0 ; i < Client.table.size() ; i++)
            if (Client.table.get(i).getKey().equalsIgnoreCase(name)) {
                disconnect();
                Client.socket = new Socket(Client.table.get(i).getValue(), 9698);
                Client.ois = new ObjectInputStream(Client.socket.getInputStream());
                Client.oos = new ObjectOutputStream(Client.socket.getOutputStream());
                break;
            }
    }
    public static void sendMessage(String message) throws IOException {
        Client.oos.writeObject(message);
    }
}
