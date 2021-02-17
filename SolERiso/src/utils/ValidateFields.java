/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author johnn
 */
public class ValidateFields {
    private static final int MINIMUM_PASSWORD_LENGTH = 3;
    
    public static Boolean passwordLength(String field) {
        return field.length() >= MINIMUM_PASSWORD_LENGTH;
    }
    
    public static Boolean isEmpty(String field) {
        return field.length() == 0;
    }
}
