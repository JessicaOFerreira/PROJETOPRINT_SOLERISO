/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Scheduling;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sql.SQLConnection;
import sql.SQLQueries;

/**
 *
 * @author Nicolas
 */
public class DaoScheduling {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public void register(Scheduling scheduling) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Scheduling.REGISTER); 
            
            this.statement.setString(1, scheduling.getReport());
            this.statement.setTime(2, scheduling.getHour());
            this.statement.setDate(3, (Date) scheduling.getDate_scheduling());
            this.statement.setFloat(4, scheduling.getPrice());
            this.statement.setInt(5, scheduling.getAdmin_id());
            this.statement.setInt(6, scheduling.getPatient_id());
            this.statement.setInt(7, scheduling.getOperation_id());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Agendamento - Contate o ADM");
        }
    }
    
    public void remove(Scheduling scheduling) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Scheduling.REMOVE); 
            
            this.statement.setInt(1, scheduling.getId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO DELETAR Agendamento - Contate o ADM");
        }
    }
    
    public void update(Scheduling scheduling) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Scheduling.UPDATE); 
            
            this.statement.setInt(1, scheduling.getId());
            this.statement.setString(2, scheduling.getReport());
            this.statement.setTime(3, scheduling.getHour());
            this.statement.setDate(4, (Date) scheduling.getDate_scheduling());
            this.statement.setFloat(5, scheduling.getPrice());
            this.statement.setInt(6, scheduling.getAdmin_id());
            this.statement.setInt(7, scheduling.getPatient_id());
            this.statement.setInt(8, scheduling.getOperation_id());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Agendamento - Contate o ADM");
        }
    }
}
