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
import utils.ErrorMessage;

/**
 *
 * @author Nicolas
 */
public class DaoPatient {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public List<Patient> list() throws DaoException {
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
    
    public Patient register(String name, String cpf, String phone, int address) throws DaoException {
        List<Patient> patients = this.list();
        
        boolean patientAlreadyExists = false;
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getCpf()== null ? cpf == null : patients.get(i).getCpf().equals(cpf)) {
                patientAlreadyExists = true;
                break;
            }
        }
        
        if (patientAlreadyExists) {
            ErrorMessage.setMessage("Este paciente já foi cadastrado");
            return null;
        }
        
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.REGISTER);
            
            this.statement.setString(1, name);
            this.statement.setString(2, cpf);
            this.statement.setString(3, phone);
            this.statement.setInt(3, address);
            
            int totalRowsAffected = this.statement.executeUpdate();
            
            this.connection.close();
            
            Patient patient;
            
            if (totalRowsAffected == 1) {
                patient = new Patient();
                
                patient.setName(name);
                patient.setCpf(cpf);
                patient.setPhoneNumber(phone);
                patient.setAddressId(address);
                
                return patient;
            } else {
                ErrorMessage.setMessage("Erro ao realizar o cadastro do paciente. Tente novamente.");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Paciente - Contate o Suporte");
        }
    }
    
    public void remove(Patient patient) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.REMOVE); 
            
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
            this.statement = connection.prepareStatement(SQLQueries.Patient.UPDATE); 
            
            this.statement.setString(1, patient.getName());
            this.statement.setString(2, patient.getCpf());
            this.statement.setString(3, patient.getPhoneNumber());
            this.statement.setInt(4, patient.getAddressId());
            this.statement.setInt(5, patient.getId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO ATUALIZAR Paciente - Contate o ADM");
        }
    }
    
    public List<Patient> list_by_name(String name) throws DaoException {
        try{
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.LISTBYNAME);
           
            this.statement.setString(1, name);
            
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
    
        public Patient show_by_cpf(int cpf) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Patient.SHOWBYCPF); 
            
            this.statement.setInt(1, cpf);

            result = this.statement.executeQuery();
            
            Patient patient = new Patient();
            
            if(result.next()){
                patient.setId(result.getInt(1));
                patient.setName(result.getString(2));
                patient.setCpf(result.getString(4));
                patient.setPhoneNumber(result.getString(5));
                patient.setAddressId(result.getInt(6));
            }else{
                throw new DaoException("PACIENTE NÃO EXISTE");
            }

            this.connection.close();
            
            return patient;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO PROCURAR Paciente - Contate o ADM");
        }
    }
    
}
