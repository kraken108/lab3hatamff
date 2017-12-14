/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jakob
 */
@Entity
@Table(name = "image")
@XmlRootElement
public class Image {
    
    
   // @Column(name="imagedata",length=20000)
    //private String imageData;
    
    @Lob
    @Column(name="imagedata",length=100000)
    private byte[] imageData;
    
    @ManyToOne
    private User user;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Image(){
        
    }

    public Image(byte[] imageData, User user) {
        this.imageData = imageData;
        this.user = user;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
