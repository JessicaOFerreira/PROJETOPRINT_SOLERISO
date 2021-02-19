/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author johnn
 */
public class Auth {
    private static Boolean isAuth;
    private static int adminLoggedIn;

    public static int getAdminLoggedIn() {
        return adminLoggedIn;
    }

    public static void setAdminLoggedIn(int adminLoggedIn) {
        Auth.adminLoggedIn = adminLoggedIn;
    }

    public Boolean getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Boolean isAuth) {
        Auth.isAuth = isAuth;
    }
}
