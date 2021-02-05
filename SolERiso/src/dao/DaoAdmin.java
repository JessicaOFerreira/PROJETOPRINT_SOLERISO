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
import services.Auth;
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
            Auth authenticated = new Auth();
            
            if (result.next()) {
                admin = new Admin();
                
                admin.setLogin(result.getString("login"));
                admin.setIsDentist(result.getBoolean("dentist"));
                
                authenticated.setIsAuth(true);
            } else {
                // No data
                authenticated.setIsAuth(false);
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
    
    @Override
    public Admin register(String login, String password, boolean dentist) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.REGISTER);
            
            System.out.println("Passou por aqui!");
            
            this.statement.setString(1, login);
            this.statement.setString(2, password);
            this.statement.setBoolean(3, dentist);
            
            this.statement.executeUpdate();
           
            this.connection.close();
            
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Dentista - Contate o ADM");
        }
    }
    
    @Override
    public Admin changePassword(Admin admin, String newPassword, int idToChange) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.CHANGEPASSWORD); 
            
            this.statement.setString(1, newPassword);
            this.statement.setInt(2, idToChange);
            
            if(admin.getIsDentist()){
                result = this.statement.executeQuery();
            }
            
            this.connection.close();
            
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO MUDAR SENHA DO Dentista - Contate o ADM");
        }
    }
}
