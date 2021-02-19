/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author johnny
 */
public class Navigation {
    
    public Navigation () {}
    
    public static void goToScreen(ActionEvent event, String destinationURL, String screenTitle, Boolean closeOriginScreen) throws IOException {
        URL url = Navigation.class.getResource(destinationURL);
        
        Parent root = FXMLLoader.load(url);
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setTitle(screenTitle);
        stage.setScene(scene);
        stage.show();
        
        if (closeOriginScreen) {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }
}
