package org.lab1.web.bean.data.extra;

import org.lab1.data.CRUD;
import org.lab1.data.entity.BookCreature;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class YoungCreaturesBean {
    public List<BookCreature> getItems(){
        return CRUD.getYoungestBookCreature();
    }
}
