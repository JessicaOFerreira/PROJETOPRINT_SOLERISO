/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nicolas
 */
public class Operation {
private int id;
    private String name;
    private String description;
    private int operation_id;
    private int active;
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDesciption() {
        return description;
    }
    public int getOperation_id() {
        return operation_id;
    }
    public int getActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }
    public void setActive(int active) {
        this.active = active;
    }
}
