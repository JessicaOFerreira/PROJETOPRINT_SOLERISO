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
         public static final String LOGIN = "SELECT * FROM admin WHERE login = ? AND admin_password = ?";
         public static final String REGISTER = "INSERT INTO admin (login, admin_password , dentist)"
            +" VALUES (?,?,?,?,?,?)";
    }
}
