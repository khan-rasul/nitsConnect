package com.lusar;

import javafx.util.Pair;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    private String name;

    public ClientThread(Socket s) {
        this.socket=s;
    }
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream( socket.getInputStream() );
            ObjectOutputStream oos = new ObjectOutputStream( socket.getOutputStream() );
            this.name = (String) ois.readObject();
            Main.table.add(new Pair<>(this.name, socket.getInetAddress()));
            while(true)
            {
                String message = (String) ois.readObject();
                if(message.equals("table"))
                {
                    oos.reset();
                    oos.writeObject(Main.table);
                }
                else
                    break;
            }
            for( int i = 0 ; i < Main.table.size() ; i++)
            {
                if(Main.table.get(i).getKey().equals(name))
                {
                    Main.table.remove(i);
                    break;
                }
            }
            System.out.println("Client disconnected...");
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Oops: " + e.getMessage());
        }
    }
}
