package com.mvorodeveloper.springframeworkpetclinic.model;

import java.io.Serializable;

/**
 * The Serializable interface is a marker interface;
 * it declares no methods at all. It tells the serialization mechanism that the class can be serialized.
 */
public class BaseEntity implements Serializable {

    /**
     * Is the Hibernate recommendation to use primitive wrapper classes instead of primitives.
     * You can't distinguish the default value of a primitive int 0 from an assigned 0
     * while there is no possible ambiguity with a null (a null id always means a new entity)
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
