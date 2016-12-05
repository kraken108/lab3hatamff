/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author micke1
 */
public class Person {
    
    private String name;
    private Profession profession;
    private String country;
    private int age;
    private String personId;
    
    public Person(String name){
        this.name=name;
        age = 0;
        country = null;
        profession = Profession.UNKNOWN;
        this.personId=null;
    }
    
    public Person(String name, int age, String country){
        this.name=name;
        this.age=age;
        this.country=country;
    }
    
    public Person(){
        this.name=null;
        this.age = 0;
        this.country = null;
        this.profession = Profession.UNKNOWN;
        this.personId=null;
    }

    /**
     * @return the firstName
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the profession
     */
    public Profession getProfession() {
        return profession;
    }

    /**
     * @param profession the profession to set
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
    }    

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the personId
     */
    public String getPersonId() {
        return personId;
    }

    /**
     * @param personId the personId to set
     */
    public void setPersonId(String personId) {
        this.personId = personId;
    }    
}
