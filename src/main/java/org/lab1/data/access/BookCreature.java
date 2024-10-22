package org.lab1.data.access;

import lombok.Data;
import org.lab1.data.entity.BookCreatureType;

import java.time.LocalDateTime;

@Data
public class BookCreature {
    private long id; // Must be greater than 0, unique, and generated automatically

    private String name; // Cannot be null, string cannot be empty

    private DataCoordinates dataCoordinates; // Cannot be null

    private LocalDateTime creationDate; // Cannot be null, generated automatically

    private long age; // Must be greater than 0

    private BookCreatureType creatureType; // Cannot be null

    private MagicCity creatureLocation; // Can be null

    private Double attackLevel; // Must be greater than 0, cannot be null

    private Ring ring; // Cannot be null

    public BookCreature() {
        this.creationDate = LocalDateTime.now(); // Automatically set creation date
    }


    public void setId(long id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0");
        this.id = id;
    }


    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }


    public void setDataCoordinates(DataCoordinates dataCoordinates) {
        if (dataCoordinates == null) throw new IllegalArgumentException("Coordinates cannot be null");
        this.dataCoordinates = dataCoordinates;
    }


    public void setAge(long age) {
        if (age <= 0) throw new IllegalArgumentException("Age must be greater than 0");
        this.age = age;
    }


    public void setCreatureType(BookCreatureType creatureType) {
        if (creatureType == null) throw new IllegalArgumentException("Creature type cannot be null");
        this.creatureType = creatureType;
    }


    public void setAttackLevel(Double attackLevel) {
        if (attackLevel == null || attackLevel <= 0)
            throw new IllegalArgumentException("Attack level must be greater than 0 and cannot be null");
        this.attackLevel = attackLevel;
    }

    public void setRing(Ring ring) {
        if (ring == null) throw new IllegalArgumentException("Ring cannot be null");
        this.ring = ring;
    }

    public static BookCreature fromEntity(org.lab1.data.entity.BookCreature entity) {
        BookCreature creature = new BookCreature();
        creature.setId(entity.getId());
        creature.setName(entity.getName());
        creature.setDataCoordinates(DataCoordinates.fromEntity(entity.getCoordinates()));
        creature.setCreationDate(entity.getCreationDate());
        creature.setAge(entity.getAge());
        creature.setCreatureType(entity.getCreatureType());
        creature.setCreatureLocation(MagicCity.fromEntity(entity.getCreatureLocation()));
        creature.setAttackLevel(entity.getAttackLevel());
        creature.setRing(Ring.fromEntity(entity.getRing()));
        return creature;
    }
}