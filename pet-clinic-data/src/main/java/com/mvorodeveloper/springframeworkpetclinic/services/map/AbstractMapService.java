package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.BaseEntity;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    private final Map<Long, T> objects = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(objects.values());
    }

    T findById(ID id) {
        return objects.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            objects.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object to save cannot be null");
        }

        return object;
    }

    void delete(T object) {
        objects.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id) {
        objects.remove(id);
    }

    private Long getNextId() {
        return objects.isEmpty() ? 1L : Collections.max(objects.keySet()) + 1;
    }
}
