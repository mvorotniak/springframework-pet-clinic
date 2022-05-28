package com.mvorodeveloper.springframeworkpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.mvorodeveloper.springframeworkpetclinic.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * In memory database represented as a Map
 */
@Slf4j
public abstract class AbstractMapService<T extends BaseEntity, I extends Long> {

    private final Map<Long, T> objects = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(objects.values());
    }

    T findById(I id) {
        return objects.get(id);
    }

    T save(T object) {
        Optional.ofNullable(object)
            .ifPresentOrElse(
                obj -> {
                if (obj.getId() == null) {
                    obj.setId(getNextId());
                }
                objects.put(obj.getId(), obj);
            }, () -> log.error("Object to save cannot be null"));

        return object;
    }

    void delete(T object) {
        objects.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    void deleteById(I id) {
        objects.remove(id);
    }

    private Long getNextId() {
        return objects.isEmpty() ? 1L : Collections.max(objects.keySet()) + 1;
    }

}
