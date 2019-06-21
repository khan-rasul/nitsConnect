package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Controller {

    @FXML
    private AnchorPane pane1;
    @FXML
    private TextField name;
    @FXML
    private Button connect;

    private String Name, string;

    public void initialize(){
            connect.setDefaultButton(true);
    }

    private void alert2(){
        string=Functions.serverConfirmation();
        String s="Message from server...\n"+string;
        Alert a1=new Alert(Alert.AlertType.INFORMATION);
        a1.setTitle("CHATBOX");
        a1.setContentText(s);
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    private void alert1(){
        Alert a1=new Alert(Alert.AlertType.ERROR);
        a1.setTitle("CHATBOX");
        a1.setContentText("Please enter a name to continue!!");
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    public void click1(ActionEvent event) throws IOException, ClassNotFoundException {
        Name = name.getText();
        if(Name.equals(""))
        {
            alert1();
        }
        else
        {
            Functions.readName( Name );
            Client.run();
            alert2();
            if(string.equals("Couldn't connect to server") )
            {
                Main.shut();
            }
            AnchorPane pane = FXMLLoader.load(getClass().getResource("table.fxml"));
            pane1.getChildren().setAll(pane);
        }
    }
}
