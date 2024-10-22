package org.lab1.web.bean.data;

import lombok.Data;
import org.lab1.data.entity.BookCreatureType;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
@Data
public class BookCreatureTypeBean {
    private List<BookCreatureType> typeList;

    public BookCreatureTypeBean() {
        typeList = List.of(BookCreatureType.values());
    }
}
