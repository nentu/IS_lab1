package org.lab1.web.bean.data;

import lombok.Getter;
import lombok.Setter;
import org.lab1.data.CRUD;
import org.lab1.data.entity.BookCreature;
import org.lab1.data.entity.Coordinates;
import org.lab1.data.entity.MagicCity;
import org.lab1.data.entity.Ring;
import org.lab1.web.bean.data.abstracts.ManagerBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "bookCreatureBean")
@SessionScoped
@Getter
@Setter
public class BookCreatureBean extends ManagerBean<BookCreature> {

    public BookCreatureBean() {
        super(BookCreature.class, "bookCreature");
    }

    @Override
    public void addItem() {
        BookCreature selectedItem = super.itemsStack.pop();

        if (selectedItem.getOwner() == null)
            selectedItem.setOwner(getCurrentOwner());

        FacesContext facesContext = FacesContext.getCurrentInstance();


        if (selectedItem.getPassedRingId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose ring", null));
            return;
        }
        if (selectedItem.getPassedCoordinatesId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose coordinates", null));
            return;
        }
        if (selectedItem.getPassedLocationId() == null) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Choose city", null));
            return;
        }
        Ring selectedRing = CRUD.find(Ring.class, selectedItem.getPassedRingId());
        selectedItem.setRing(selectedRing);

        Coordinates selectedCoord = CRUD.find(Coordinates.class, selectedItem.getPassedCoordinatesId());
        selectedItem.setCoordinates(selectedCoord);

        MagicCity selectedCity = CRUD.find(MagicCity.class, selectedItem.getPassedLocationId());
        selectedItem.setCreatureLocation(selectedCity);

        CRUD.add(selectedItem);
        System.out.println("editItem: " + selectedItem);
    }

    @Override
    public void editItem() {
        BookCreature selectedItem = super.itemsStack.pop();

        if (selectedItem.getOwner() == null)
            selectedItem.setOwner(getCurrentOwner());

        if (selectedItem.getPassedRingId() != selectedItem.getRing().getId()) {
            Ring selectedRing = CRUD.find(Ring.class, selectedItem.getPassedRingId());
            selectedItem.setRing(selectedRing);
        }

        if (selectedItem.getPassedCoordinatesId() != selectedItem.getCoordinates().getId()) {
            Coordinates selectedCoord = CRUD.find(Coordinates.class, selectedItem.getPassedCoordinatesId());
            selectedItem.setCoordinates(selectedCoord);
        }

        if (selectedItem.getPassedLocationId() != selectedItem.getCreatureLocation().getId()) {
            MagicCity selectedCity = CRUD.find(MagicCity.class, selectedItem.getPassedLocationId());
            selectedItem.setCreatureLocation(selectedCity);
        }

        CRUD.update(selectedItem);
        System.out.println("editItem: " + selectedItem);

        editStack();
    }


    @Override
    public List<Long> getIdList() {
        return getItems().stream().map(BookCreature::getId).collect(Collectors.toList());

    }

    @Override
    public void emptyInstance() {
        super.getItemsStack().push(new BookCreature());
        super.getSelectedItem().setId(-1);
        super.getSelectedItem().setCoordinates(
                new Coordinates()
        );

        super.getSelectedItem().setCreatureLocation(
                new MagicCity()
        );

        super.getSelectedItem().setRing(
                new Ring()
        );
    }


    @Override
    public List<String> getFieldNames() {
        return List.of(
                "id",
                "name",
                "coordinatesId",
                "creationDate",
                "age",
                "creatureType",
                "creatureLocationId",
                "attackLevel",
                "ringId"
        );
    }
}
