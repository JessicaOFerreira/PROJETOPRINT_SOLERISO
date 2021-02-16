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
import java.util.ArrayList;
import java.util.List;
import services.Auth;
import sql.SQLConnection;
import sql.SQLQueries;
import utils.ErrorMessage;

/**
 *
 * @author johnn
 */
public class DaoAdmin implements IDaoAdmin {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public List<Admin> list() throws DaoException {
        try{
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.LIST);
           
            result = this.statement.executeQuery();
            
            List<Admin> admins = new ArrayList<>();
            
            Admin admin;
            
            while (result.next()) {
                admin = new Admin();
                admin.setLogin(result.getString("login"));
                admins.add(admin);
            }
            this.connection.close();

            return admins;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO LISTAR administradores - Contate o suporte");
        }
    }

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
        List<Admin> admins = this.list();
        
        boolean userAlreadyExists = false;
        for (int i = 0; i < admins.size(); i++) {
            System.out.println(admins.get(i).getLogin());
            
            if (admins.get(i).getLogin() == null ? login == null : admins.get(i).getLogin().equals(login)) {
                userAlreadyExists = true;
                break;
            }
        }
        
        if (userAlreadyExists) {
            ErrorMessage.setMessage("Este usuário já existe");
            return null;
        }
        
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Admin.REGISTER);
            
            this.statement.setString(1, login);
            this.statement.setString(2, password);
            this.statement.setBoolean(3, dentist);
            
            int totalRowsAffected = this.statement.executeUpdate();
            
            this.connection.close();
            
            Admin admin = null;
            Auth authenticated = new Auth();
            
            if (totalRowsAffected == 1) {
                admin = new Admin();
                
                admin.setLogin(login);
                admin.setIsDentist(dentist);
                
                authenticated.setIsAuth(true);
                
                return admin;
            } else {
                ErrorMessage.setMessage("Erro ao realizar o cadastro. Tente novamente.");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Administrador - Contate o Suporte");
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
