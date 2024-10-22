package org.lab1.data.access;

import lombok.Data;

@Data
public class Ring {
    private long id; // Primary key, auto-generated

    private String name; // Cannot be null, string cannot be empty

    private long weight; // Must be greater than 0

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    public void setWeight(long weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0");
        this.weight = weight;
    }

    public static Ring fromEntity(org.lab1.data.entity.Ring entity){
        Ring ring = new Ring();
        ring.setId(entity.getId());
        ring.setName(entity.getName());
        ring.setWeight(entity.getWeight());
        return ring;
    }
}