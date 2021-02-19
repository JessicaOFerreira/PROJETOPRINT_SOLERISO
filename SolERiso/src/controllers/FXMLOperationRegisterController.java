/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoOperation;
import entities.Operation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.Routes;
import utils.ErrorMessage;
import utils.Loading;

/**
 *
 * @author johnn
 */
public class FXMLOperationRegisterController {
    
    private String errorMessage;
    
    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionField;
    
    @FXML
    private Label fieldErrorMessage;
    
    @FXML
    void goToOperations(ActionEvent event) throws Exception {
        Routes.render(event, "/operations", true);
    }
    
    Boolean validateFields() {
        if (nameField.getText().length() == 0) {
            errorMessage = "O campo nome deve ser preenchido";
            return false;
        }
        
        return true;
    }
    
    void renderError(String message) {
        fieldErrorMessage.setText(message);
        fieldErrorMessage.setVisible(true);
        Loading.close();
    }
    
    @FXML
    void register(ActionEvent event) throws Exception {
        Loading.show();
        
        Boolean fieldsAreValid = validateFields();
        
        if (fieldsAreValid) {
            // Fields
            Operation operation = new Operation();
            
            operation.setName(nameField.getText());
            operation.setDescription(descriptionField.getText());
            operation.setActive(1);
            
            DaoOperation daoOperation = new DaoOperation();
            
            Operation newOperation = daoOperation.register(operation);
            
            if (newOperation != null) {
                Loading.close();
                Routes.render(event, "/operations", true);
            } else {
                this.renderError(ErrorMessage.message);
            }
        } else {
            this.renderError(errorMessage);
        }
    }
}
