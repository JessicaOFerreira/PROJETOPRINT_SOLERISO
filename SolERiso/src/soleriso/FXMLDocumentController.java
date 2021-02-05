/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soleriso;

import dao.DaoAdmin;
import entities.Admin;
import exceptions.DaoException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import services.Auth;
import utils.Navigation;

/**
 *
 * @author johnn
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private CheckBox isDentistCheck;

    @FXML
    private Button loginButton;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;
    
    @FXML
    void goToRegisterScreen(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/screens/FXMLRegister.fxml");
        Navigation.goToScreen(event, url, "Cadastre-se", true);
    }
    
    @FXML
    void login(ActionEvent event) throws IOException, DaoException {
        String user = userField.getText();
        String password = passwordField.getText();
        Boolean isDentist = isDentistCheck.isSelected();
        
        DaoAdmin daoAdmin = new DaoAdmin();
        
        Admin response = daoAdmin.login(user, password);
                
        Auth authenticated = new Auth();
        Boolean isAuth = authenticated.getIsAuth();
       
        if (isAuth) {
            URL url = getClass().getResource("/screens/FXMLDashboard.fxml");
            Navigation.goToScreen(event, url, "Dashboard", true);
        } else {
            System.out.println("Usuário e senha incorreto");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Initialize");
        
        
    }    
    
}
