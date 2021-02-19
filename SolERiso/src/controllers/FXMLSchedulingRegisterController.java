/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoAddress;
import dao.DaoOperation;
import dao.DaoPatient;
import entities.Address;
import entities.Operation;
import entities.Patient;
import exceptions.DaoException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import services.Routes;
import utils.ErrorMessage;
import utils.Loading;

/**
 *
 * @author johnn
 */
public class FXMLSchedulingRegisterController implements Initializable {

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField hourField;

    @FXML
    private ComboBox<String> patientSelect;

    @FXML
    private TextArea reportField;

    @FXML
    private ComboBox<String> operationSelect;

    @FXML
    private Label fieldErrorMessage;
    
    @FXML
    void goToSchedulings(ActionEvent event) throws Exception {
        Routes.render(event, "/schedulings", true);
    }
    
    @FXML
    void register(ActionEvent event) throws Exception {
        
    }
    
    public List<Operation> loadOperations () throws DaoException {
        DaoOperation daoOperation = new DaoOperation();
        List<Operation> operations = daoOperation.list();
        return operations;
    }
    
    public List<Patient> loadPatients () throws DaoException {
        DaoPatient daoPatient = new DaoPatient();
        List<Patient> patients = daoPatient.list();
        return patients;
    }
    
    private void renderOperationList(List<Operation> operations) {
        List<String> operationLabels = new ArrayList<>();
        
        for (int i = 0; i < operations.size(); i++) {
            operationLabels.add(operations.get(i).getName());
        }
        
        ObservableList<String> observableList = FXCollections.observableArrayList(operationLabels);
     
        operationSelect.setItems(observableList);
    }
    
    private void renderPatientList(List<Patient> patients) {
        List<String> patientLabels = new ArrayList<>();
        
        for (int i = 0; i < patients.size(); i++) {
            patientLabels.add(patients.get(i).getName() + " - " + patients.get(i).getCpf());
        }
        
        ObservableList<String> observableList = FXCollections.observableArrayList(patientLabels);
     
        patientSelect.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Carregar todos os pacientes
            List<Patient> patients = loadPatients();
            // Carregar todos os procedimentos
            List<Operation> operations = loadOperations();
            
            this.renderPatientList(patients);
            this.renderOperationList(operations);
            
        } catch (Exception ex) {
            System.out.println("Erro ao buscar lista de pacientes ou procedimentos");
        }
    } 
    
//    Boolean validateFields() {
//        if (nameField.getText().length() == 0 ||
//            cpfField.getText().length() == 0 ||
//            phoneField.getText().length() == 0 ||
//            streetField.getText().length() == 0 ||
//            houseNumberField.getText().length() == 0 || 
//            neighborhoodField.getText().length() == 0 || 
//            cityField.getText().length() == 0)
//        {
//            errorMessage = "Todos os campos devem ser preenchidos";
//            return false;
//        }
//        
//        return true;
//    }
//    
//    void renderError(String message) {
//        fieldErrorMessage.setText(message);
//        fieldErrorMessage.setVisible(true);
//        Loading.close();
//    }
//    
//    @FXML
//    void register(ActionEvent event) throws Exception {
//        Loading.show();
//        
//        Boolean fieldsAreValid = validateFields();
//        
//        if (fieldsAreValid) {
//            // Fields
//            String name = nameField.getText();
//            String cpf = cpfField.getText();
//            String phone = phoneField.getText();
//            String street = streetField.getText();
//            String houseNumber = houseNumberField.getText();
//            String neighborhood = neighborhoodField.getText();
//            String city = cityField.getText();
//            
//            DaoAddress daoAdress = new DaoAddress();
//            
//            Address address = daoAdress.register(street, houseNumber, neighborhood, city);
//            
//            if (address != null) {
//                DaoPatient daoPatient = new DaoPatient();
//                
//                Patient patient = daoPatient.register(name, cpf, phone, address.getAddressId());
//                
//                if (patient != null) {
//                    Loading.close();
//                    Routes.render(event, "/patients", true);
//                } else {
//                    this.renderError(ErrorMessage.message);
//                }
//            } else {
//                this.renderError("Ocorreu um erro ao cadastrar o endereço do paciente");
//            }
//        } else {
//            this.renderError("Todos os campos devem ser preenchidos");
//        }
//    }
}
