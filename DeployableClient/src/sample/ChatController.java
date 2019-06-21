package sample;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChatController {
    @FXML
    private TextArea textArea;
    @FXML
    public TextArea textArea2 = new TextArea() ;
    @FXML
    private TextField textField;
    @FXML
    private AnchorPane pane3;
    @FXML
    private Button send;
    public void initialize()
    {
        send.setDefaultButton(true);
        textArea.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        textArea2.setWrapText(true);
        textArea.setWrapText(true);
        new Thread(() -> {
            while(true)
            {
                try {
                    mes = (String ) Client.ois.readObject();
                    mes = Encryption.decipher( mes );
                    Platform.runLater(() -> textArea2.appendText( mes + "\n") );
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static String message, mes;
    public void send() throws IOException {
        message = textField.getText();
        textField.clear();
        Functions.sendMessage( Encryption.cipher( message ) );
        textArea.appendText(message + "\n");
    }

    public void exit()
    {
        Main.shut();
    }
    public void click4() throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("sample.fxml"));
        pane3.getChildren().setAll(pane);
    }
}
