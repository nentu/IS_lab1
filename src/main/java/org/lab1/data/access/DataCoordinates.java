package org.lab1.data.access;

import lombok.*;
import org.lab1.data.entity.Coordinates;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
@SessionScoped
public class DataCoordinates {
    private long id; // Primary key, auto-generated

    public void setX(Long x) {
        System.out.println("Set new X for:" + this);
        this.x = x;
    }
//
//
//    public Object getX() {
//        return x;
//    }


    private Long x; // Cannot be null

    private float y;

    public static DataCoordinates fromEntity(Object o) {
        Coordinates entity = (Coordinates) o;
        return new DataCoordinates(
                entity.getId(), entity.getX(), entity.getY()
        );
    }
}