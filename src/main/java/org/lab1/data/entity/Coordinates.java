package org.lab1.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lab1.web.bean.data.Identable;

@Entity
@Table(name = "coordinates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates implements Identable, Ownerable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Primary key, auto-generated

    @Column(nullable = false)
    private Long x = 0L; // Cannot be null

    @Column()
    private float y;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // Cannot be null

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}