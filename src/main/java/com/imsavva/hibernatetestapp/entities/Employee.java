package com.imsavva.hibernatetestapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A simple Hibernate entity configured with annotations.
 * 
 * @author Savva Kodeikin
 *
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = 787814707940038624L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "ADDED_AT", nullable = false)
    private Date addedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date date) {
        this.addedAt = date;
    }

    @Override
    public String toString() {
        return String.format("Employee [id: %s, name: %s, surname: %s, added at: %s]", id, name, surname, addedAt);
    }
}
