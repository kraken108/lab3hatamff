/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael
 */

@Entity
@Table(name = "DatasetValues")
@XmlRootElement
public class DatasetValue {
    
    private int x;
    private int y;
    
    public DatasetValue(){
        
    }
    
    
    
    
}
