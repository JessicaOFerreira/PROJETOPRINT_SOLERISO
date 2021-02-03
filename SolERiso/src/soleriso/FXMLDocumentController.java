/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soleriso;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.Navigation;

/**
 *
 * @author johnn
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    void goToRegisterScreen(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/screens/FXMLRegister.fxml");
        Navigation.goToScreen(event, url, "Cadastre-se", true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Initialize");
    }    
    
}
