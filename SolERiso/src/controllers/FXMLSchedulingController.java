/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.DaoOperation;
import dao.DaoScheduling;
import entities.Operation;
import entities.Scheduling;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
public class FXMLSchedulingController implements Initializable {
    
    @FXML
    private TableView<Scheduling> schedulingListTable;
    
    @FXML
    private TableColumn<Scheduling, String> patientColumn;

    @FXML
    private TableColumn<Scheduling, String> patientCPFColumn;
    
    @FXML
    private TableColumn<Scheduling, Date> dateColumn;

    @FXML
    private TableColumn<Scheduling, String> hourColumn;
    
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
    
    private void renderSchedulingList(List<Scheduling> schedulings) {
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
        patientCPFColumn.setCellValueFactory(new PropertyValueFactory<>("patient_cpf"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date_scheduling"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hour"));
        
        ObservableList<Scheduling> observableList = FXCollections.observableArrayList(schedulings);
                
        schedulingListTable.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DaoScheduling daoScheduling  = new DaoScheduling();
        
        try {
            List<Scheduling> schedulings = daoScheduling.list();
            
            renderSchedulingList(schedulings);
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Erro ao buscar lista de agendamentos");
        }
        
    }
}
