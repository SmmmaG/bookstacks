package com.paragonsoftware.db;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.MappedSuperclass;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author IvIsmakaev
 */
@MappedSuperclass
public abstract class AuditableEntity {

    public abstract Long getId();

    public Map<String, Object> createCurrentAttributesMap() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(this, Map.class);
    }

    public Map<String, Object[]> createDiff(final AuditableEntity unchangedEntity) {
        Map<String, Object> currentObjectMap = createCurrentAttributesMap();
        Map<String, Object> unchangedObjectMap = unchangedEntity.createCurrentAttributesMap();
        Map<String, Object[]> resultMap = new HashMap<>();
        Set<String> allSet = new HashSet<>(currentObjectMap.keySet());
        allSet.addAll(unchangedObjectMap.keySet());
        // get diff
        allSet.forEach(key -> {
            // get added values
            if (unchangedObjectMap.get(key) == null) {
                resultMap.put(key, new Object[]{null, currentObjectMap.get(key)});
                currentObjectMap.remove(key);
            } else if (currentObjectMap.get(key) == null) {
                resultMap.put(key, new Object[]{unchangedObjectMap.get(key), null});
                unchangedObjectMap.remove(key);
            } else if (unchangedObjectMap.get(key) != null && currentObjectMap.get(key) != null
                    && (!unchangedObjectMap.get(key).equals(currentObjectMap.get(key)))) {
                resultMap.put(key, new Object[]{unchangedObjectMap.get(key), currentObjectMap.get(key)});
                currentObjectMap.remove(key);
                unchangedObjectMap.remove(key);
            }
        });

        return resultMap;
    }
}
