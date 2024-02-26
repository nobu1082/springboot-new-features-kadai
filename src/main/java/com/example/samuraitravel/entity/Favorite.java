package com.example.samuraitravel.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "favorite")
@Data
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User userid; 
    
    @ManyToOne
    @JoinColumn(name = "houseid")
    private House houseid;
    
    @Column(name ="isfavorite")
    private Boolean isfavorite;
    
    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp created_at;

}
   
    
    
    