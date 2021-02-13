/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Jéssica Oliveira
 */
public class Address {
    private int address_id;
    private int house_number;
    private String city;
    private String neighborhood;
    private String street;
    
    public int getAddressId() {
        return address_id;
    }
    public int getHouse_number() {
        return house_number;
    }
    public String getCity() {
        return city;
    }
    public String getNeighborhood() {
        return neighborhood;
    }  
    public String getStreet() {
        return street;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public void setStreet(String street) {
        this.street = street;
    }
}
