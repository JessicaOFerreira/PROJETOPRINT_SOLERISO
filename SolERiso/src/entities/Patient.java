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
public class Patient {
    private int id;
    private String name;
    private String cpf;
    private String phone_number;
    private int address_id;
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public String getPhoneNumber() {
        return phone_number;
    }
    public int getAddressId() {
        return address_id;
    }

    
    public void setId(int id) {
        this.id = id;
    }    
    public void setName(String name) {
        this.name = name;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }
    public void setAddressId(int address_id) {
        this.address_id = address_id;
    }
}


