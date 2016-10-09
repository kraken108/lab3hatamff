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


public class Student extends Person {

	private String program;
	private int credits;
	
	public Student(String name, int age, String program, int credits) {
		super(name, age ); // Call constructor in super class
		this.program = program;
		this.credits = credits;
	}
	
        public Student(){
            super();
            program="None";
            credits=0;
        }
        
        
        
	public void addCredits(int newCredits) {
		if(newCredits > 0) credits += newCredits;
	}
	
	/* To reference inherited private fields name and age,
	 * use the public access methods (also inherited).
	 */
	public String toString() {
		String info = super.getName() + ", program  " + program;
		info += ", age  " + super.getAge() + ", " + credits + " credits";
		return info;
	}
}

