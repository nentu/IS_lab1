package org.lab1.web.bean.data.extra;

import lombok.Data;
import org.lab1.data.CRUD;
import org.lab1.data.entity.BookCreature;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
public class StrongerBean {
    private Double minAttackLvl = -10000D;

    public List<BookCreature> getItems(){
        return CRUD.getStronger(minAttackLvl);
    }
}
