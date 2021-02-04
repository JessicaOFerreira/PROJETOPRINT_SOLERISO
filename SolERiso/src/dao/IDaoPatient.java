/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import exceptions.DaoException;

/**
 *
 * @author Nicolas
 */
public interface IDaoPatient {
    public Patient register(String name, String cpf, String phone_number, int address_id) throws DaoException;
    public Patient list() throws DaoException;
    public Patient delete(int patient_id) throws DaoException;
    public Patient update(int patient_id, String name, String cpf, String phone_number, int address_id) throws DaoException;
}
