/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Address;
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
public class DaoAddress {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public Address show_by_patient_id(int id) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Address.SHOWBYPATIENTID); 
            
            this.statement.setInt(1, id);

            result = this.statement.executeQuery();
            
            Address address = new Address();
            
            if(result.next()){
                address.setAddress_id(result.getInt(1));
                address.setCity(result.getString(2));
                address.setHouse_number(result.getInt(3));
                address.setNeighborhood(result.getString(4));
                address.setStreet(result.getString(4));
            }else{
                throw new DaoException("ENDEREÇO NÃO EXISTE");
            }

            this.connection.close();
            
            return address;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO PROCURAR Endereço - Contate o ADM");
        }
    }
}
