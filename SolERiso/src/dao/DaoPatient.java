/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sql.SQLConnection;
import sql.SQLQueries;

/**
 *
 * @author Nicolas
 */
public class DaoPatient {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public void register(Patient patient) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.REGISTER); 
            
            this.statement.setString(1, patient.getName());
            this.statement.setString(2, patient.getCpf());
            this.statement.setString(3, patient.getPhoneNumber());
            this.statement.setInt(4, patient.getAddressId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Paciente - Contate o ADM");
        }
    }
    
    public List<Patient> getContatos() throws DaoException {
        try{
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.LIST);
           
            result = this.statement.executeQuery();
            
            List<Patient> patients = new ArrayList<>();
            
            Patient patient;
            
            while (result.next()) {
                patient = new Patient();
                patients.add(patient);
            }
            this.connection.close();

            return patients;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO LISTAR Pacientes - Contate o ADM");
        }
    }
    
    public void remove(Patient patient) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.REGISTER); 
            
            this.statement.setInt(1, patient.getId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO DELETAR Paciente - Contate o ADM");
        }
    }
    
    public void update(Patient patient, int Id) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.REGISTER); 
            
            this.statement.setInt(1, patient.getId());
            this.statement.setString(2, patient.getName());
            this.statement.setString(3, patient.getCpf());
            this.statement.setString(4, patient.getPhoneNumber());
            this.statement.setInt(5, patient.getAddressId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO ATUALIZAR Paciente - Contate o ADM");
        }
    }
    
}
