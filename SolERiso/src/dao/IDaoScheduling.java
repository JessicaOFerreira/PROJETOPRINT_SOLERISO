/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Scheduling;
import exceptions.DaoException;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Nicolas
 */
public interface IDaoScheduling {
    public Scheduling register(String report, Time hour, Date date_scheduling, float price, int admin_id, int patient_id, int operation_id) throws DaoException;
    public Scheduling remove(int scheduling) throws DaoException;
    public Scheduling update(String report, Time hour, Date date_scheduling, float price, int admin_id, int patient_id, int operation_id, int id) throws DaoException;
}
