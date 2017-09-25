/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BO;

/**
 *
 * @author Anders
 */
public class TestClass {
    private static String name;
    
    public TestClass(){
        
    }
    
    public static String getName(){
        return name;
    }
    
    public static void setName(String name){
        TestClass.name = name;
    }
    
}
