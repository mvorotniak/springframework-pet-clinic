package com.mvorodeveloper.springframeworkpetclinic.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * The Serializable interface is a marker interface;
 * it declares no methods at all. It tells the serialization mechanism that the class can be serialized.
 *
 * Annotated with MappedSuperclass, which means we want to inherit from this class.
 * This object is not going to be created in the database.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    /**
     * Is the Hibernate recommendation to use primitive wrapper classes instead of primitives.
     * You can't distinguish the default value of a primitive int 0 from an assigned 0
     * while there is no possible ambiguity with a null (a null id always means a new entity)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public boolean isNew() {
        return this.id == null;
    }

}
