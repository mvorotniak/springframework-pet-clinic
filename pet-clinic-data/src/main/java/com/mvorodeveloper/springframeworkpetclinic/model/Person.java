package com.mvorodeveloper.springframeworkpetclinic.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Marked with MappedSuperclass annotation (not an inherited annotation)
 * as we inherit from this class and we don't want this entity in the database
 */
@MappedSuperclass
public class Person extends BaseEntity {

    // The column name is a little redundant as Hibernate automatically converts camel case into sneak case,
    // but we still want to declare this
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}