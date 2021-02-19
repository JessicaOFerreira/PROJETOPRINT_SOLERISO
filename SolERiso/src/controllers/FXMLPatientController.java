/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoPatient;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Routes;

/**
 *
 * @author johnn
 */
public class FXMLPatientController implements Initializable {
    
    @FXML
    private TableView<Patient> patientListTable;
    
    @FXML
    private TableColumn<Patient, String> nameColumn;
    
    @FXML
    void goTo(ActionEvent event) throws IOException, Exception {
        final Node source = (Node) event.getSource();
        String route = source.getId();
        
        String destinationRoute;
        
        switch (route) {
            case "goToDashboard":
                destinationRoute = "/dashboard";
            break;
            case "goToPatientRegister":
                destinationRoute = "/patient-register";
            break;
            default:
                 throw new IllegalArgumentException("Destino não encontrado no evento");
        }
        
        Routes.render(event, destinationRoute, true);
    }
    
    private void renderPatientList(List<Patient> patients) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        ObservableList<Patient> observableList = FXCollections.observableArrayList(patients);
                
        patientListTable.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DaoPatient daoPatient = new DaoPatient();
        
        try {
            List<Patient> patients = daoPatient.list();
            
            renderPatientList(patients);
        } catch (Exception ex) {
            System.out.println("Erro ao buscar lista de pacientes");
        }
        
    } 
}
