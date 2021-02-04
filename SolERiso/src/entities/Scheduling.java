/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Nicolas
 */
public class Scheduling {
    private int id;
    private String report;
    private Time hour;
    private Date date_scheduling;
    private float price;
    private int admin_id;
    private int patient_id;
    private int operation_id;
    
    public int getId(){
        return id;
    }
    
    public String getReport() {
        return report;
    }

    public Time getHour() {
        return hour;
    }
        
    public Date getDate_scheduling() {
        return date_scheduling;
    }

    public float getPrice() {
        return price;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public int getOperation_id() {
        return operation_id;
    }

    
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setDate_scheduling(Date date_scheduling) {
        this.date_scheduling = date_scheduling;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setReport(String report) {
        this.report = report;
    }
        
    
    
}

