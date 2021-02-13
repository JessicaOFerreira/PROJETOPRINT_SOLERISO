/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Operation;
import exceptions.DaoException;

/**
 *
 * @author Nicolas
 */
public interface IDaoOperation {
    public Operation register(String name, String description) throws DaoException;
    public Operation list() throws DaoException;
    public Operation update(String name, String description, int operation_id) throws DaoException;
    public Operation remove(int operation_id) throws DaoException;
    public Operation show(int operation_id) throws DaoException;
}
