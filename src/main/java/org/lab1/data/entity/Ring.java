package org.lab1.data.entity;

import javax.persistence.*;

import lombok.*;
import org.lab1.web.bean.data.Identable;

@Entity
@Table(name = "rings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ring implements Identable, Ownerable {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false)
    private String name; // Cannot be null, string cannot be empty

    @Column(nullable = false)
    private long weight; // Must be greater than 0

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null

    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }


    public void setWeight(long weight) {
        if (weight <= 0) throw new IllegalArgumentException("Weight must be greater than 0");
        this.weight = weight;
    }
}