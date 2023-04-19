package com.demo.app.entity;

public class Department {
    private Long id;
    private String name;
    private Long current_budget;

    public Department(){}

    public Department(String name,Long current_budget){
        this.current_budget=current_budget;
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
    public Long getCurrent_budget() {
        return current_budget;
    }
    public void setCurrent_budget(Long current_budget) {
        this.current_budget = current_budget;
    }
    @Override
    public String toString() {
        return "Department [current_budget=" + current_budget + ", id=" + id + ", name=" + name + "]";
    }
}
