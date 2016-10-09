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
public class FL5A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person p1 = new Person("Linders Andström",55);
        System.out.println(p1.toString());
        Employee e1 = new Employee("Städare",12000,p1.getName(),p1.getAge());
        System.out.println(e1.toString());
        Student s1 = new Student("Kalle",23,"datateknik",180);
        System.out.println(s1.toString());
        Person personer[]={p1,e1,s1};
        
        
        
        for(int i=0; i<personer.length; i++){
            if(personer[i] instanceof Person){
                System.out.println(personer[i].toString());
            }
        }
        
        
    }
    
}
