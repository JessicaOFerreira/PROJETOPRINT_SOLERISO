/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Operation;
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
public class DaoOperation {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;
    
    public void check_operation(Scheduling operation) throws DaoException {
        this.connection = SQLConnection.getConnectionInstance();
        this.statement = connection.prepareStatement(SQLQueries.Operation.VERIFYREGISTERED);

        this.statement.setString(1, operation.getName());

        return this.statement.executeQuery();
    }

    public void register(Operation operation) throws DaoException {
        try {
            boolean registered = this.check_operation(operation);

            if (registered) {
                ErrorMessage.setMessage("Operação já registrada");
                return null;
            }

            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Operation.REGISTER); 
            
            this.statement.setString(1, operation.getName());
            this.statement.setString(2, operation.getDesciption());
            
            result = this.statement.executeUpdate();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO SALVAR Procedimento - Contate o ADM");
        }
    }
    
    public List<Operation> getContatos() throws DaoException {
        try{
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Operation.LIST);
           
            result = this.statement.executeQuery();
            
            List<Operation> Operations = new ArrayList<>();
            
            Operation operation;
            
            while (result.next()) {
                operation = new Operation();
                Operations.add(operation);
            }
            this.connection.close();

            return Operations;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO LISTAR Procedimentos - Contate o ADM");
        }
    }
    
    public void update(Operation operation) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Operation.UPDATE); 
            
            this.statement.setString(1, operation.getName());
            this.statement.setString(2, operation.getDesciption());
            this.statement.setInt(3, operation.getId());
            
            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO ATUALIZAR Procedimento - Contate o ADM");
        }
    }
    
    public void remove(int id) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Operation.REMOVE); 
            
            this.statement.setInt(1, id);

            result = this.statement.executeQuery();
           
            this.connection.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO APAGAR Procedimento - Contate o ADM");
        }
    }
    
    public Operation show_by_id(int id) throws DaoException {
        try {
            this.connection = SQLConnection.getConnectionInstance();
            this.statement = connection.prepareStatement(SQLQueries.Operation.SHOWBYID); 
            
            this.statement.setInt(1, id);

            result = this.statement.executeQuery();
            
            Operation operation = new Operation();
            
            if(result.next()){
                operation.setId(result.getInt(1));
                operation.setName(result.getString(2));
                operation.setDescription(result.getString(3));
                operation.setActive(result.getInt(4));
            }else{
                throw new DaoException("ALUNO NÃO EXISTE");
            }

            this.connection.close();
            
            return operation;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DaoException("PROBLEMA AO PROCURAR Procedimento - Contate o ADM");
        }
    }
    
}
