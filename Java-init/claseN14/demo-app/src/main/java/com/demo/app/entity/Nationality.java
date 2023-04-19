package com.demo.app.entity;

public class Nationality {

    private Long id;
    private String name;

    public Nationality(){}
    public Nationality(String name){
        this.name=name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Nationality [id=" + id + ", name=" + name + "]";
    }
    
}
