package org.lab1.web.bean.data.extra;

import lombok.Data;
import org.lab1.data.CRUD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Data
public class RingSwapBean {
    private long creatureId1;
    private long creatureId2;

    public void doIt(){
        CRUD.swapRings(creatureId1, creatureId2);
    }
}
