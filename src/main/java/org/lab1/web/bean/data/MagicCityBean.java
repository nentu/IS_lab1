package org.lab1.web.bean.data;

import lombok.Getter;
import lombok.Setter;
import org.lab1.data.entity.MagicCity;
import org.lab1.web.bean.data.abstracts.UsedManagerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "magicCityBean")
@SessionScoped
@Getter
@Setter
public class MagicCityBean extends UsedManagerBean<MagicCity> {


    public MagicCityBean() {
        super(MagicCity.class, "cityEditPanel", "cityComponentDialog");
    }


    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(MagicCity::getId).collect(Collectors.toList());
    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new MagicCity());
        super.getSelectedItem().setId(-1);
        super.getSelectedItem().setEstablishmentDate(LocalDateTime.now());
    }

    @Override
    public List<String> getFieldNames() {
        return List.of("id",
                "name",
                "area",
                "population",
                "establishmentDate",
                "governor",
                "capital",
                "populationDensity"
        );
    }


}
