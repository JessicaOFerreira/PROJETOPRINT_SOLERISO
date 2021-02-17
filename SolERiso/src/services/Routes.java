/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import javafx.event.ActionEvent;
import utils.Navigation;

/**
 *
 * @author johnn
 */
public class Routes {
    
    public static void render(ActionEvent event, String route, boolean closeOriginScreen) throws IOException, Exception {
        String destinationURL;
        String destinationScreenName;
        
        String screensPath = "/screens";
        
        switch (route) {
            case "/login":
                destinationURL = screensPath + "/FXMLLogin.fxml";
                destinationScreenName = "Login";
            break;
            case "/register":
                destinationURL = screensPath + "/FXMLRegister.fxml";
                destinationScreenName = "Cadastre-se";
            break;
            case "/dashboard":
                destinationURL = screensPath + "/FXMLDashboard.fxml";
                destinationScreenName = "Dashboard";
            break;
            case "/patients":
                destinationURL = screensPath + "/FXMLPatient.fxml";
                destinationScreenName = "Pacientes";
            break;
            case "/patient-register":
                destinationURL = screensPath + "/FXMLPatientRegister.fxml";
                destinationScreenName = "Novo paciente";
            break;
            default:
                throw new IllegalArgumentException("Página não encontrada");
        }
        
        Navigation.goToScreen(event, destinationURL, destinationScreenName, closeOriginScreen);
    }
}
