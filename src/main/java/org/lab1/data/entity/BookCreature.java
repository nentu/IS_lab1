package org.lab1.data.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Setter;
import org.lab1.web.bean.data.Identable;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_creatures")
@Data
@SqlResultSetMapping(
        name = "bookCreatureMapping",
        entities = @EntityResult(
                entityClass = BookCreature.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "name", column = "name"),
                        @FieldResult(name = "coordinates", column = "coordinates_id"),
                        @FieldResult(name = "creationDate", column = "creation_date"),
                        @FieldResult(name = "age", column = "age"),
                        @FieldResult(name = "creatureType", column = "creature_type"),
                        @FieldResult(name = "creatureLocation", column = "creature_location_id"),
                        @FieldResult(name = "attackLevel", column = "attack_level"),
                        @FieldResult(name = "ring", column = "ring_id"),
                        @FieldResult(name = "owner", column = "user_id")
                }
        )
)
public class BookCreature implements Identable, Ownerable{

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Must be greater than 0, unique, and generated automatically

    @Column(nullable = false)
    private String name; // Cannot be null, string cannot be empty

    @ManyToOne
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates; // Cannot be null

    @Transient
    private Long coordinatesId;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate; // Cannot be null, generated automatically

    @Column(nullable = false)
    private long age; // Must be greater than 0

    @Enumerated(EnumType.STRING)
    @Column(name = "creature_type", nullable = false)
    private BookCreatureType creatureType; // Cannot be null

    @ManyToOne
    @JoinColumn(name = "creature_location_id")
    private MagicCity creatureLocation; // Can be null

    @Transient
    private Long creatureLocationId;

    @Column(name = "attack_level", nullable = false)
    private Double attackLevel = 0.001; // Must be greater than 0, cannot be null

    @ManyToOne
    @JoinColumn(name = "ring_id", nullable = false)
    private Ring ring; // Cannot be null

    @Transient
    private Long ringId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null

    public Long getPassedRingId(){
        return ringId;
    }

    public Long getPassedCoordinatesId(){
        return coordinatesId;
    }

    public Long getPassedLocationId(){
        return creatureLocationId;
    }

    public Long getRingId() {;
        return ring.getId();
    }

    public Long getCreatureLocationId() {
        return creatureLocation.getId();
    }

    public Long getCoordinatesId() {
        return coordinates.getId();
    }

    public BookCreature() {
        this.creationDate = LocalDateTime.now(); // Automatically set creation date
    }


    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }


    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new IllegalArgumentException("Coordinates cannot be null");
        this.coordinates = coordinates;
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
}