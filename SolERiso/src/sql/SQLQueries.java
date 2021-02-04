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
        public static final String LIST =      "SELECT * FROM patient";
        public static final String DELETE =    "DELETE FROM patient WHERE patient_id = ?";
        public static final String UPDATE =    "UPDATE patient"
                                             + "WHERE patient_id = ?"
                                             + "SET name = ?"
                                             + "SET cpf = ?"
                                             + "SET phone_number = ?"
                                             + "SET address_id = ?";
    }
    
}
