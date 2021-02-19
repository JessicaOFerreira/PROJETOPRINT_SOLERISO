/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoAddress;
import dao.DaoAdmin;
import dao.DaoPatient;
import entities.Address;
import entities.Admin;
import entities.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.Auth;
import services.Routes;
import utils.ErrorMessage;
import static utils.ErrorMessage.message;
import utils.Loading;
import utils.ValidateFields;

/**
 *
 * @author johnn
 */
public class FXMLPatientRegisterController {
    
    private String errorMessage;
    
    @FXML
    private TextField streetField;

    @FXML
    private TextField houseNumberField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField neighborhoodField;

    @FXML
    private TextField phoneField;
    
    @FXML
    void goToPatients(ActionEvent event) throws Exception {
        Routes.render(event, "/patients", true);
    }
    
    @FXML
    private Label fieldErrorMessage;
    
    Boolean validateFields() {
        if (nameField.getText().length() == 0 ||
            cpfField.getText().length() == 0 ||
            phoneField.getText().length() == 0 ||
            streetField.getText().length() == 0 ||
            houseNumberField.getText().length() == 0 || 
            neighborhoodField.getText().length() == 0 || 
            cityField.getText().length() == 0)
        {
            errorMessage = "Todos os campos devem ser preenchidos";
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
            String name = nameField.getText();
            String cpf = cpfField.getText();
            String phone = phoneField.getText();
            String street = streetField.getText();
            String houseNumber = houseNumberField.getText();
            String neighborhood = neighborhoodField.getText();
            String city = cityField.getText();
            
            DaoAddress daoAdress = new DaoAddress();
            
            Address address = daoAdress.register(street, houseNumber, neighborhood, city);
            
            if (address != null) {
                DaoPatient daoPatient = new DaoPatient();
                
                Patient patient = daoPatient.register(name, cpf, phone, address.getAddressId());
                
                if (patient != null) {
                    Loading.close();
                    Routes.render(event, "/patients", true);
                } else {
                    this.renderError(ErrorMessage.message);
                }
            } else {
                this.renderError("Ocorreu um erro ao cadastrar o endereço do paciente");
            }
        } else {
            this.renderError("Todos os campos devem ser preenchidos");
        }
    }
}
