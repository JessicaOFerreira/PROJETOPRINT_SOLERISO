/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import services.Routes;

/**
 *
 * @author johnn
 */
public class FXMLSchedulingController {
    
    @FXML
    void goTo(ActionEvent event) throws IOException, Exception {
        final Node source = (Node) event.getSource();
        String route = source.getId();
        
        String destinationRoute;
        
        switch (route) {
            case "goToDashboard":
                destinationRoute = "/dashboard";
            break;
            case "goToSchedulingRegister":
                destinationRoute = "/scheduling-register";
            break;
            default:
                 throw new IllegalArgumentException("Destino não encontrado no evento");
        }
        
        Routes.render(event, destinationRoute, true);
    }
}
