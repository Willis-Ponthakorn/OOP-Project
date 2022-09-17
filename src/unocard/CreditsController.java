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
public class CreditsController implements Initializable {

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
    private void HandleBacktomain2(ActionEvent event) throws IOException {
        Stage stage = (Stage) MainMenu.getScene().getWindow();
        // do what you have to do
        stage.close();
        Parent Back = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene HTPScene2 = new Scene(Back);
        
        //new stage
        Stage window4 = (Stage)((Node)event.getSource()).getScene().getWindow();
        window4.setScene(HTPScene2);
        window4.show();
    }
    
}
