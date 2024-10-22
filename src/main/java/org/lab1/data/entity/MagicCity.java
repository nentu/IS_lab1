package org.lab1.data.entity;

import javax.persistence.*;

import lombok.*;
import org.lab1.web.bean.data.Identable;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "magic_cities")
public class MagicCity implements Identable, Ownerable {

    // Optional: can be managed by JPA
    // Getters and Setters
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false)
    private String name; // Cannot be null, string cannot be empty

    @Column(nullable = false)
    private int area = 1; // Must be greater than 0

    @Column(nullable = false)
    private long population = 1; // Must be greater than 0

    // Can be null
    @Setter
    @Column(name = "establishment_date")
    private LocalDateTime establishmentDate; // Can be null

    // Can be null
    @Setter
    @Enumerated(EnumType.STRING)
    private BookCreatureType governor; // Can be null

    @Setter
    @Column(nullable = false)
    private boolean capital; // Indicates if it is a capital city

    @Column(name = "population_density")
    private Float populationDensity = 0.0001F; // Must be greater than 0

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    public void setArea(int area) {
        if (area <= 0) throw new IllegalArgumentException("Area must be greater than 0");
        this.area = area;
    }

    public void setPopulation(long population) {
        if (population <= 0) throw new IllegalArgumentException("Population must be greater than 0");
        this.population = population;
    }

    public void setPopulationDensity(Float populationDensity) {
        if (populationDensity != null && populationDensity <= 0)
            throw new IllegalArgumentException("Population density must be greater than 0");
        this.populationDensity = populationDensity;
    }

}