/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Admin;
import exceptions.DaoException;

/**
 *
 * @author johnn
 */
public interface IDaoAdmin {
    public Admin login(String login, String password) throws DaoException;
    public Admin register(String login, String password, boolean dentist) throws DaoException;
    public Admin changePassword(Admin admin, String newPassword, int idToChange) throws DaoException;
}
