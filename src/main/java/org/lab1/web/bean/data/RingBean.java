package org.lab1.web.bean.data;

import org.lab1.data.entity.Ring;
import org.lab1.web.bean.data.abstracts.UsedManagerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "ringBean")
@SessionScoped
public class RingBean extends UsedManagerBean<Ring> {

    public RingBean() {
        super(Ring.class, "ringEditPanel", "ringComponentDialog");
    }

    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(Ring::getId).collect(Collectors.toList());

    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new Ring());
        super.getSelectedItem().setId(-1);
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id", "name", "weight");
    }

}
