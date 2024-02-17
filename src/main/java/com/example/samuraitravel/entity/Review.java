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
 @Table(name = "review")
 @Data
public class Review {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private Integer id;
 
     @Column(name = "name")
     private String name;
 
     @Column(name = "commenttext")
     private String commenttext;
 
     @Column(name = "value")
     private Integer value;
     
     @ManyToOne
     @JoinColumn(name = "houseid")
     private House houseid; 
     
     @Column(name = "updated_at", insertable = false, updatable = false)
     private Timestamp updatedAt;
	}
