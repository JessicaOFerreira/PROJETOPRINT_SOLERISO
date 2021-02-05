/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author johnn
 */
public class Admin {
    private String login;
    private String password;
    private Boolean isDentist;

    public String getId() {
        return login;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsDentist() {
        return isDentist;
    }

    public void setIsDentist(Boolean isDentist) {
        this.isDentist = isDentist;
    }
}
