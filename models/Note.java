package com.example.Notes.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Note{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;

    protected Note(){};

    protected Note(String title,String description){
        this.title=title;
        this.description=description;
    }
    //getters and setters
    public void setTitle(String title){
        this.title=title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
}

