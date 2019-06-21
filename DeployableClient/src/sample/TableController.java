package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

public class TableController {
    @FXML
    private AnchorPane pane2;
    @FXML
    private TextField name2;
    @FXML
    private Button submit2;

    @FXML
    private ListView<String> listView = new ListView<>();

    private Runnable acceptor = new Runnable()
    {
        @Override
        public void run() {
            while(!AcceptingRequests.flag )
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("chat.fxml"));
                        pane2.getChildren().setAll(pane);
                    } catch (IOException e)
                    {
                        System.out.println("IOException in acceptorThread " + e.getMessage() );
                    }
                }
            });
        }
    };
    private Thread acceptorThread = new Thread(acceptor);

    public void initialize() throws IOException, ClassNotFoundException {
        submit2.setDefaultButton(true);
        loadData();
        acceptorThread.start();
    }


    private ObservableList list = FXCollections.observableArrayList();
    private void loadData() throws IOException, ClassNotFoundException {

        Vector<Pair< String, InetAddress>> table;
        list.removeAll( list );

        table = Functions.readTable();
        if( table != null )
            for( int i = 0 ; i < table.size() ; i++)
                list.add( table.get( i ).getKey() );
        listView.getItems().clear();
        listView.getItems().addAll( list );
    }

    private void alert1(){
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("CHATBOX");
        a1.setContentText("Please enter a name to continue!!");
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    public void click3() throws IOException{
        String Name = name2.getText();
        if( Name.equals("") ) {
            alert1();
        }
        else {
            acceptorThread.interrupt();
            Functions.establishConnection(Name);
            AnchorPane pane = FXMLLoader.load(getClass().getResource("chat.fxml"));
            pane2.getChildren().setAll(pane);
        }
    }
    public void disconnect() {
        Functions.disconnect();
        Main.shut();
    }

    public void Refresh() throws IOException, ClassNotFoundException {
        loadData();
    }
}
