/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fl5a;

/**
 * Super class for type Person.
 */

public class Person {

	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
        
        public Person(){
            name="Unknown";
            age=0;
        }
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String toString() {
		String info = name + ", age " + age;
		return info;
	}
}