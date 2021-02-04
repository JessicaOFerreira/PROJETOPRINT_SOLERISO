/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Admin;
import exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sql.SQLConnection;
import sql.SQLQueries;

/**
 *
 * @author johnn
 */
public class DaoAdmin implements IDaoAdmin {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    @Override
    public Admin login(String login, String password) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.LOGIN);
            this.statement.setString(1, login);
            this.statement.setString(2, password);     
            
            result = this.statement.executeQuery();
            
            Admin admin = null;
            
            if (result.next()) {
                admin = new Admin();
                
                admin.setLogin(result.getString("login"));
                admin.setIsDentist(result.getBoolean("dentist"));
            } else {
                // No data
                this.connection.close();
                return null;
            }
            
            this.connection.close();
            
            return admin;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DaoException("Login error");
        }
    }
    
    public void register(Admin admin) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.REGISTER); 
            
            this.statement.setString(1, admin.getLogin());
            this.statement.setString(2, admin.getPassword());
            this.statement.setBoolean(3, admin.getIsDentist());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Dentista - Contate o ADM");
        }
    }

    @Override
    public Admin register(String login, String password_admin, boolean dentist) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
