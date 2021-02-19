/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoAddress;
import dao.DaoOperation;
import dao.DaoPatient;
import dao.DaoScheduling;
import entities.Address;
import entities.Operation;
import entities.Patient;
import entities.Scheduling;
import exceptions.DaoException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import services.Auth;
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
    private ComboBox<String> hourSelect;

    @FXML
    private ComboBox<String> patientSelect;

    @FXML
    private TextArea reportField;

    @FXML
    private ComboBox<String> operationSelect;

    @FXML
    private Label fieldErrorMessage;
    
    private String errorMessage;
    
    private List<Patient> patients;
    
    private List<Operation> operations;
    
    @FXML
    void goToSchedulings(ActionEvent event) throws Exception {
        Routes.render(event, "/schedulings", true);
    }
    
    public Patient getSelectedPatient(List<Patient> patients, String selectedPatientLabel) {
        String objLabel;
        
        for (int i = 0; i < patients.size(); i++) {
            objLabel = patients.get(i).getName() + " - " + patients.get(i).getCpf();
            
            if (objLabel.equals(selectedPatientLabel)) {
                return patients.get(i);
            }
        }
        
        return null;
    }
    
    public Operation getSelectedOperation(List<Operation> operations, String selectedOperationLabel) {
        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).getName().equals(selectedOperationLabel)) {
                return operations.get(i);
            }
        }
        
        return null;
    }
    
    Boolean validateFields() {
        if (patientSelect.getValue() == null ||
            operationSelect.getValue() == null ||
            dateField.getValue() == null ||
            hourSelect.getValue() == null ||
            priceField.getText().length() == 0)
        {
            errorMessage = "Os campos marcados com * são obrigatórios";
            return false;
        }
        
        try {
            Float.parseFloat(priceField.getText());
        } catch (Exception exp) {
            errorMessage = "O campo preço deve conter apenas valores numéricos";
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
            Scheduling scheduling = new Scheduling();
            
            Patient selectedPatient = this.getSelectedPatient(this.patients, patientSelect.getValue());
            Operation selectedOperation = this.getSelectedOperation(this.operations, operationSelect.getValue());
            
            scheduling.setAdmin_id(Auth.getAdminLoggedIn());
            scheduling.setPatient_id(selectedPatient.getId());
            scheduling.setOperation_id(selectedOperation.getId());
            scheduling.setDate_scheduling(dateField.getValue());
            scheduling.setHour(hourSelect.getValue() + ":00:00");
            scheduling.setPrice(Float.parseFloat(priceField.getText()));
            scheduling.setReport(reportField.getText());
            
            DaoScheduling daoScheduling = new DaoScheduling();
            
            Scheduling newScheduling = daoScheduling.register(scheduling);
            
            if (newScheduling != null) {
                Loading.close();
                Routes.render(event, "/schedulings", true);
            } else {
                this.renderError(ErrorMessage.message);
            }
        } else {
            this.renderError(errorMessage);
        }
    }
    
    public List<Operation> loadOperations () throws DaoException {
        DaoOperation daoOperation = new DaoOperation();
        return daoOperation.list();
    }
    
    public List<Patient> loadPatients () throws DaoException {
        DaoPatient daoPatient = new DaoPatient();
        return daoPatient.list();
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
    
    private void renderHourList() {
        String hours[] = { "7", "8", "9", "10", "11", "13", "14", "15", "16", "17", "18", "19" }; 
        
        ObservableList<String> observableList = FXCollections.observableArrayList(hours);
     
        hourSelect.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Carregar todos os pacientes
            this.patients = loadPatients();
            // Carregar todos os procedimentos
            this.operations = loadOperations();
            
            this.renderPatientList(this.patients);
            this.renderOperationList(this.operations);
            this.renderHourList();
        } catch (Exception ex) {
            System.out.println("Erro ao buscar lista de pacientes ou procedimentos");
        }
    } 
}
