/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Address;
import exceptions.DaoException;

/**
 *
 * @author Nicolas
 */
public interface IDaoAddress {
    public Address show_by_patient_id(int id) throws DaoException;
}
