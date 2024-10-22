package org.lab1.data.access;

import lombok.Data;
import org.lab1.data.entity.BookCreatureType;

import java.time.LocalDateTime;

@Data
public class MagicCity {

    // Optional: can be managed by JPA
    // Getters and Setters


    private long id; // Primary key, auto-generated
    private String name; // Cannot be null, string cannot be empty
    private int area; // Must be greater than 0
    private long population; // Must be greater than 0
    private LocalDateTime establishmentDate; // Can be null
    private BookCreatureType governor; // Can be null
    private boolean capital; // Indicates if it is a capital city
    private Float populationDensity; // Must be greater than 0


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

    public static MagicCity fromEntity(org.lab1.data.entity.MagicCity entity) {
        MagicCity magicCity = new MagicCity();
        magicCity.setId(entity.getId());
        magicCity.setName(entity.getName());
        magicCity.setArea(entity.getArea());
        magicCity.setPopulation(entity.getPopulation());
        magicCity.setPopulationDensity(entity.getPopulationDensity());
        magicCity.setCapital(entity.isCapital());
        magicCity.setEstablishmentDate(entity.getEstablishmentDate());
        magicCity.setGovernor(entity.getGovernor());
        return magicCity;
    }
}