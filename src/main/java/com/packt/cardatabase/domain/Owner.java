package com.packt.cardatabase.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ownerid;

    private String firstname, lastname;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;

    // @ManyToMany(cascade = CascadeType.PERSIST)
    // @JoinTable(name = "car_owner", joinColumns = 
    // {
    //     @JoinColumn(name = "ownerid")
    // }, inverseJoinColumns = 
    // {
    //     @JoinColumn(name = "id")
    // })
    // private Set<Car> cars = new HashSet<>();

    public Owner() {

    }

    public Owner(String firstname, String lastname){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getOwnerid(){
        return ownerid;
    }

    public void setOwnerid(Long ownerid){
        this.ownerid = ownerid;
    }

    public String getFirstname(){
        return firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public List<Car> getCars(){
        return cars;
    }

    public void setCars(List<Car> cars){
        this.cars = cars;
    }

    // public Set<Car> getCars(){
    //     return cars;
    // }

    // public void setCars(Set<Car> cars){
    //     this.cars = cars;
    // }
}