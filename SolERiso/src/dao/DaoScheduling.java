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
import java.sql.SQLException;
import sql.SQLConnection;
import sql.SQLQueries;
import utils.ErrorMessage;

/**
 *
 * @author Nicolas
 */
public class DaoScheduling {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public boolean checkSchedule(Scheduling scheduling) throws DaoException, SQLException {
        this.connection = SQLConnection.getConnectionInstance();
        this.statement = connection.prepareStatement(SQLQueries.Scheduling.VERIFYFREETIME);

        this.statement.setTime(1, scheduling.getHour());
        this.statement.setDate(2, (Date) scheduling.getDate_scheduling());

        result = this.statement.executeQuery();

        return result.next();
    }

    public Scheduling register(Scheduling scheduling) throws DaoException {
        try {
            boolean schedule = this.checkSchedule(scheduling);

            if (schedule) {
                ErrorMessage.setMessage("Horario não disponível");
                return null;
            }

            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Scheduling.REGISTER); 
            
            this.statement.setString(1, scheduling.getReport());
            this.statement.setTime(2, scheduling.getHour());
            this.statement.setDate(3, (Date) scheduling.getDate_scheduling());
            this.statement.setFloat(4, scheduling.getPrice());
            this.statement.setInt(5, scheduling.getAdmin_id());
            this.statement.setInt(6, scheduling.getPatient_id());
            this.statement.setInt(7, scheduling.getOperation_id());
            
            int totalRowsAffected = this.statement.executeUpdate();
           
            this.connection.close();
            
            if (totalRowsAffected == 1) {
                return scheduling;
            } else {
                ErrorMessage.setMessage("Erro ao realizar o cadastro do agendamento. Tente novamente.");
                return null;
            }
            
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

            this.statement.setString(1, scheduling.getReport());
            this.statement.setTime(2, scheduling.getHour());
            this.statement.setDate(3, (Date) scheduling.getDate_scheduling());
            this.statement.setFloat(4, scheduling.getPrice());
            this.statement.setInt(5, scheduling.getAdmin_id());
            this.statement.setInt(6, scheduling.getPatient_id());
            this.statement.setInt(7, scheduling.getOperation_id());
            this.statement.setInt(8, scheduling.getId());
            
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Agendamento - Contate o ADM");
        }
    }
}
