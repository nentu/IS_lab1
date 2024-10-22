package org.lab1.web.bean.data.extra;

import lombok.Data;
import org.lab1.data.CRUD;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
@Data
public class RemoveByRingBean {
    private long ringId;

    public void doIt(){
        CRUD.deleteBookCreaturesByRingId(ringId);
    }
}
