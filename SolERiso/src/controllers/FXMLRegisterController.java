/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoAdmin;
import entities.Admin;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.Auth;
import utils.ErrorMessage;
import utils.Loading;
import utils.Navigation;
import utils.ValidateFields;

/**
 *
 * @author johnn
 */
public class FXMLRegisterController {
    
    private String errorMessage;
    
    @FXML
    private TextField usernameField;

    @FXML
    private Button registerButton;

    @FXML
    private Button cancelRegisterButton;

    @FXML
    private CheckBox isDentist;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backToLoginButton;

    @FXML
    private PasswordField confirmPasswordField;
    
    @FXML
    private Label fieldErrorMessage;
    
    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        URL url = getClass().getResource("/soleriso/FXMLDocument.fxml");
        Navigation.goToScreen(event, url, "Login", true);
    }
    
    Boolean validateFields(String username, String password, String confirmPassword) {
        if (username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
            errorMessage = "Todos os campos devem ser preenchidos";
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            errorMessage = "As senhas não coincidem";
            return false;
        }
        
        if (!ValidateFields.passwordLength(password)) {
            errorMessage = "A senha deve possuir no mínimo 3 dígitos";
            return false;
        }
        
        return true;
    }
    
    @FXML
    void register(ActionEvent event) throws IOException, Exception {
        Loading.show();
        
        // Fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        boolean dentist = isDentist.isSelected();
        
        Boolean fieldsAreValid = validateFields(username, password, confirmPassword);
        
        if (fieldsAreValid) {
                DaoAdmin daoAdmin = new DaoAdmin();
        
                Admin response = daoAdmin.register(username, password, dentist);

                if (response == null) {
                    fieldErrorMessage.setText(ErrorMessage.getMessage());
                    fieldErrorMessage.setVisible(true);
                    Loading.close();
                } else {
                    Auth authenticated = new Auth();
                    Boolean isAuth = authenticated.getIsAuth();

                    if (isAuth) {
                        URL url = getClass().getResource("/screens/FXMLDashboard.fxml");
                        Navigation.goToScreen(event, url, "Dashboard", true);
                        Loading.close();
                    } else {
                        System.out.println("Ocorreu um erro inesperado ao fazer o cadastro!");
                        Loading.close();
                    }
                }
        } else {
            fieldErrorMessage.setText(errorMessage);
            fieldErrorMessage.setVisible(true);
            Loading.close();
        }
    }
    
}
