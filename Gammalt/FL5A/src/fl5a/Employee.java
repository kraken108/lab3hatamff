/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fl5a;

/**
 *
 * @author micke1
 */

        
public class Employee extends Person {
    

    private String position;
    private int salary;
    
    public Employee(String position, int salary, String name, int age){
        super(name,age);
        this.position=position;
        this.salary=salary;
    }
    
    public void raiseSalary(int amount){
        salary+=amount;
    }
        
    public Employee(){
        super();
        position="None";
        salary=0;
    }
    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString(){
        String info=super.toString();
        info+=" position: " + position + " salary: " + salary;
        return info;        
    }
    
    
    
}
