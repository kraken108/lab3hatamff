package model;

/**
<<<<<<< HEAD
*
* @author Jakob Danielsson & Michael Hjälmö
*/

/*
*Person is an object that holds information about an Artist or Director etc
*
*/
/*
 * Person is an object that holds information about an Artist or Director etc
 * @author Jakob Danielsson & Michael Hjälmö
 */

public class Person {
    
    private String personId;
    private String name;
    private Profession profession;
    private String country;
    private int age;
    
    /*
    *@param name the name to set
    *
    */
    public Person(String name){
        this.name=name;
        age = 0;
        country = null;
        profession = Profession.UNKNOWN;
        personId = "0";
    }
    /*
    *
    * Constructor for persons with unknown fields.
    */
    public Person(){
        this.name=null;
        this.age = 0;
        this.country = null;
        this.profession = Profession.UNKNOWN;
        personId = "0";
    }
    
    /*
    *
    *@param name the name to set
    *@param age the age to set
    *@param country the country to set
    */
    public Person(String name, int age, String country){
        this.name=name;
        this.age=age;
        this.country=country;
    }
    
    /*
    *@param personId the personId to set
    *@param name the name to set
    *@param country the country to set
    *@param profession the profession to set
    **@param age the age to set
    */
    public Person(String personId, String name,String country,Profession profession,int age){
        this.personId = personId;
        this.name = name;
        this.country = country;
        this.profession = profession;
        this.age = age;
    }

    /**
     * @param name the name to set
     */
    public void setFirstName(String name) {
        this.setName(name);
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

    /**
     * @return the name
     */
    public String getName() {
        return name;

    }   
}

    
    

