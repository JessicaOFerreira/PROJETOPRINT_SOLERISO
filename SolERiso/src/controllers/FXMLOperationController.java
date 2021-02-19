package controllers;

import dao.DaoOperation;
import entities.Operation;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnn
 */
public class FXMLOperationController implements Initializable {
    
    @FXML
    private TableView<Operation> operationListTable;
    
    @FXML
    private TableColumn<Operation, String> nameColumn;
    
    @FXML
    private TableColumn<Operation, String> descriptionColumn;
    
    @FXML
    void goTo(ActionEvent event) throws IOException, Exception {
        final Node source = (Node) event.getSource();
        String route = source.getId();
        
        String destinationRoute;
        
        switch (route) {
            case "goToDashboard":
                destinationRoute = "/dashboard";
            break;
            case "goToOperationRegister":
                destinationRoute = "/operation-register";
            break;
            default:
                 throw new IllegalArgumentException("Destino não encontrado no evento");
        }
        
        Routes.render(event, destinationRoute, true);
    }
    
    private void renderOperationList(List<Operation> operations) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("desciption"));
        
        ObservableList<Operation> observableList = FXCollections.observableArrayList(operations);
                
        operationListTable.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DaoOperation daoOperation = new DaoOperation();
        
        try {
            List<Operation> operations = daoOperation.list();
            
            renderOperationList(operations);
        } catch (Exception ex) {
            System.out.println("Erro ao buscar lista de procedimentos");
        }
        
    }
}
