package com.demo.app.domain;

import com.demo.app.annotations.Id;
import com.demo.app.annotations.OneToOne;
import com.demo.app.annotations.Table;

@Table(name="employee")
public class Employee {

    @Id
    private Long id;
    private Long dni;
    private String first_name;
    private String last_name;
    @OneToOne
    private Nationality nationality;
    @OneToOne
    private Department department;

    public Employee(){}
    public Employee(long dni,String first_name,String last_name){
        this.dni=dni;
        this.first_name=first_name;
        this.last_name=last_name;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public Nationality getNationality() {
        return nationality;
    }
    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return "Employee [department=" + department +",id="+id+ ", dni=" + dni + ", first_name=" + first_name + ", last_name="
                + last_name + ", nationality=" + nationality + "]";
    }
    
    
}
