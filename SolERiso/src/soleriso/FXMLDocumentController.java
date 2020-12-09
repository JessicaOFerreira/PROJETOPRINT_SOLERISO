/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soleriso;

import dao.DaoAdmin;
import dao.IDaoAdmin;
import entities.Admin;
import exceptions.DaoException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sql.SQLConnection;

/**
 *
 * @author johnn
 */
public class FXMLDocumentController implements Initializable {
    
    private Connection connection;
    private final IDaoAdmin daoAdmin = new DaoAdmin();
    private Admin admin;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws DaoException {
        // System.out.println("You clicked me!");
        // label.setText("Hello World, Sol&Riso!");
        
        admin = daoAdmin.login("admin@admin.com", "mudar123");
        
        System.out.println(admin.getLogin());
        System.out.println(admin.getIsDentist());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Initialize");
        this.connection = SQLConnection.getConnectionInstance();
    }    
    
}
