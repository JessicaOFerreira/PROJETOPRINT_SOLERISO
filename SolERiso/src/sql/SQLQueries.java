/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

/**
 *
 * @author johnn
 */
public class SQLQueries {

    private SQLQueries() {}
    
    public static class Admin {
        public static final String LIST   =    "SELECT * FROM admin";
        public static final String LOGIN =     "SELECT * FROM admin WHERE login = ? AND admin_password = ?";
        public static final String REGISTER =  "INSERT INTO admin (login, admin_password , dentist) VALUES (?,?,?)";
        public static String CHANGEPASSWORD =  "UPDATE admin"
                                             + "SET admin_password = ?"
                                             + "WHERE admin_id = ?";
    }
    public static class Patient {
        public static final String REGISTER =  "INSERT INTO patient (name, cpf, phone_number, address_id)"
                                             + "VALUES (?,?,?,?)";
        public static final String LIST   =    "SELECT * FROM patient";
        public static final String REMOVE =    "DELETE FROM patient WHERE patient_id = ?";
        public static final String UPDATE =    "UPDATE patient"
                                             + "SET name = ?"
                                             + ", cpf = ?"
                                             + ", phone_number = ?"
                                             + ", address_id = ?"
                                             + "WHERE patient_id = ?";
        public static final String LISTBYNAME = "SELECT * FROM patient"
                                             + "WHERE name = ?";
        public static final String SHOWBYCPF = "SELECT * FROM patient"
                                             + "WHERE cpf = ?";
    }
    public static class Scheduling {
        public static final String REGISTER =  "INSERT INTO scheduling (report, hour, date_scheduling, price, admin_id, patient_id, operation_id)"
                                             + "VALUES (?,?,?,?,?,?,?)";
        public static final String LIST   =    "SELECT * FROM scheduling";
        public static final String REMOVE =    "DELETE FROM scheduling WHERE scheduling_id = ?";
        public static final String UPDATE =    "UPDATE scheduling"
                                             + "SET report = ?"
                                             + ", hour = ?"
                                             + ", date_scheduling = ?"
                                             + ", price = ?"
                                             + ", admin_id = ?"
                                             + ", patient_id = ?"
                                             + ", operation_id = ?"
                                             + "WHERE scheduling_id = ?";
        public static final String VERIFYFREETIME=    "SELECT * " 
                                                    + "FROM scheduling "
                                                    + "WHERE hour = ? AND date_scheduling = ?";
        
    }
    
    public static class Operation {
       public static final String REGISTER =  "INSERT INTO operation (name, description)"
                                            + "VALUES (?,?)";
       public static final String LIST =      "SELECT * FROM operation";
       public static final String UPDATE =    "UPDATE operation"
                                            + "SET name = ?"
                                            + ", description = ?"
                                            + "WHERE operation_id = ?";
       public static final String REMOVE =    "UPDATE operation"
                                            + "SET active = 0"
                                            + "WHERE operation_id = ?";
       
       public static final String SHOWBYID =  "SELECT * FROM operation "
                                            + "WHERE operation_id = ?";
       public static final String VERIFYREGISTERED =  "SELECT * " 
                                                    + "FROM operation "
                                                    + "WHERE name = ?";
    }
 
    public static class Address {
       public static final String SHOWBYPATIENTID =  "SELECT * FROM address "
                                                    + "WHERE patient_id = ?";
       public static final String REGISTER = "INSERT INTO address (street, house_number, neighborhood, city)"
                                            + "VALUES (?,?,?,?)";
    }
    
}
