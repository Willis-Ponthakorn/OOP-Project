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
public class HTP1Controller implements Initializable {

    @FXML
    private Button Back2;
    @FXML
    private Button MainMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Handleback2(ActionEvent event) throws IOException {
        Parent HowToPlay2 = FXMLLoader.load(getClass().getResource("HTP.fxml"));
        Scene HTPScene2 = new Scene(HowToPlay2);
        
        //new stage
        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window2.setScene(HTPScene2);
        window2.show();
        
    }

    @FXML
    private void HandleBackTomain(ActionEvent event) throws IOException {
        Stage stage2 = (Stage) MainMenu.getScene().getWindow();
        // do what you have to do
        stage2.close();
        Parent HowToPlay = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene HTPScene = new Scene(HowToPlay);
        
        //new stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HTPScene);
        window.show();
        
    }
    
}
