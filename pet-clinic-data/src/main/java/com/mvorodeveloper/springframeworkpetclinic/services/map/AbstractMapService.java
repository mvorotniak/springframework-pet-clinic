package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    private final Map<ID, T> objects = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(objects.values());
    }

    T findById(ID id) {
        return objects.get(id);
    }

    T save(ID id, T object) {
        objects.put(id, object);

        return object;
    }

    void delete(T object) {
        objects.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(ID id) {
        objects.remove(id);
    }
}
