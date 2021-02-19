/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;

/**
 *
 * @author Nicolas
 */
public class Scheduling {
    private int id;
    private String report;
    private String hour;
    private LocalDate date_scheduling;
    private float price;
    private int admin_id;
    private int patient_id;
    private int operation_id;
    private String patient_name;
    private String patient_cpf;

    public String getPatient_cpf() {
        return patient_cpf;
    }

    public void setPatient_cpf(String patient_cpf) {
        this.patient_cpf = patient_cpf;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
    
    public int getId(){
        return id;
    }
    
    public String getReport() {
        return report;
    }

    public String getHour() {
        return hour;
    }
        
    public LocalDate getDate_scheduling() {
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

    public void setDate_scheduling(LocalDate date_scheduling) {
        this.date_scheduling = date_scheduling;
    }

    public void setHour(String hour) {
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

