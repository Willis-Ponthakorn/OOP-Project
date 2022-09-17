/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author User
 */
public class MenuController implements Initializable {

    @FXML
    private Button button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HandleStartAction(ActionEvent event) throws IOException {
        
        //go to game
        Stage stage = (Stage) button1.getScene().getWindow();
        stage.close();
        Parent GamePlay = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene MainScene = new Scene(GamePlay);
        
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        window.show();
        System.out.println("Start");
    }

    @FXML
    private void HandleHowtoplay(ActionEvent event) throws IOException {
        Stage stage = (Stage) Button2.getScene().getWindow();
        // do what you have to do
        stage.close();
        Parent HowToPlay = FXMLLoader.load(getClass().getResource("HTP.fxml"));
        Scene HTPScene = new Scene(HowToPlay);
        
        //new stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HTPScene);
        window.show();
        System.out.println("Dont know how to play?");
    }

    @FXML
    private void HandleExitGame(ActionEvent event) {
        System.out.println("Bye!");
        Stage stage = (Stage) Button3.getScene().getWindow();
        // do what you have to do
        stage.close();
        
    }

    @FXML
    private void HandleCredits(ActionEvent event) throws IOException {
        Stage stage = (Stage) Button4.getScene().getWindow();
        // do what you have to do
        stage.close();
        Parent Credits = FXMLLoader.load(getClass().getResource("Credits.fxml"));
        Scene HTPScene = new Scene(Credits);
        
        //new stage
        Stage window3 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window3.setScene(HTPScene);
        window3.show();
        System.out.println("Wanna know who make this?");
    }
    
}
