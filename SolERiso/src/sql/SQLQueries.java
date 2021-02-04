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
        public static final String LOGIN =     "SELECT * FROM admin WHERE login = ? AND admin_password = ?";
        public static final String REGISTER =  "INSERT INTO admin (login, admin_password , dentist)"
                                             + " VALUES (?,?,?)";
    }
    public static class Patient {
        public static final String REGISTER =  "INSERT INTO patient (name, cpf, phone_number, address_id)"
                                             + " VALUES (?,?,?,?)";
        public static final String LIST   =    "SELECT * FROM patient";
        public static final String REMOVE =    "DELETE FROM patient WHERE patient_id = ?";
        public static final String UPDATE =    "UPDATE patient"
                                             + "WHERE patient_id = ?"
                                             + "SET name = ?"
                                             + "SET cpf = ?"
                                             + "SET phone_number = ?"
                                             + "SET address_id = ?";
    }
    public static class Scheduling {
        public static final String REGISTER =  "INSERT INTO scheduling (report, hour, date_scheduling, price, admin_id, patient_id, operation_id)"
                                             + " VALUES (?,?,?,?,?,?,?)";
        public static final String REMOVE =    "DELETE FROM scheduling WHERE scheduling_id = ?";
        public static final String UPDATE =    "UPDATE scheduling"
                                             + "WHERE scheduling_id = ?"
                                             + "SET report = ?"
                                             + "SET hour = ?"
                                             + "SET date_scheduling = ?"
                                             + "SET price = ?"
                                             + "SET admin_id = ?"
                                             + "SET patient_id = ?"
                                             + "SET operation_id = ?";
        
    }
    
}
