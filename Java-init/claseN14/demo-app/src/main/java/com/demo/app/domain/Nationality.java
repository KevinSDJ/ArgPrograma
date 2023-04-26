package com.demo.app.domain;

import com.demo.app.annotations.Id;
import com.demo.app.annotations.Table;

@Table(name = "nationality")
public class Nationality {

    @Id
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
