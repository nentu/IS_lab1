package org.lab1.web.bean.data.abstracts;

import lombok.Data;
import org.lab1.data.CRUD;
import org.lab1.data.entity.Ownerable;
import org.lab1.data.entity.User;
import org.lab1.web.bean.auth.UserBean;
import org.lab1.web.bean.data.BookCreatureBean;
import org.lab1.web.bean.data.Identable;
import org.primefaces.PrimeFaces;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@ManagedBean(name = "manedgerBean")
@SessionScoped
@Data
public abstract class ManagerBean<T extends Ownerable & Identable> {
    protected final Class<T> classType;
    protected final String editPanelName;
    protected final String componentDialogName;
    public T selectedItem;

    protected Stack<T> itemsStack;

    protected T getSelectedItem() {
        return itemsStack.peek();
    }

    public ManagerBean(Class<T> classType, String editPanelName, String componentDialogName) {
        this.editPanelName = editPanelName;
        this.componentDialogName = componentDialogName;
        this.itemsStack = new Stack<>();
        this.classType = classType;

        emptyInstance();
    }

    public void setSelectedItem(T selectedItem) {
        itemsStack.push(selectedItem);
        System.out.println("selectedItem: " + selectedItem);
    }

    public abstract List<Long> getIdList();

    public abstract void emptyInstance();

    public void addItem() {
        T selectedItem = itemsStack.pop();
        if (selectedItem.getOwner() == null)
            selectedItem.setOwner(getCurrentOwner());
        CRUD.add(selectedItem);
        System.out.println("addItem: " + selectedItem);
    }

    protected User getCurrentOwner(){
        Map<String, Object> session =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return ((UserBean) session.get("userBean")).toEntity();
    }


    public void editStack() {
        if ((!itemsStack.empty()) && (itemsStack.peek().getId() > 0)) {
            PrimeFaces.current().ajax().update(editPanelName);
            PrimeFaces.current().executeScript("PF('" + componentDialogName + "').show()");
        }
    }

    public List<T> getItems() {
        return CRUD.findAll(classType);
    }

    public void editItem() {
        T selectedItem = itemsStack.pop();
        if (selectedItem.getOwner() == null)
            selectedItem.setOwner(getCurrentOwner());
        CRUD.update(selectedItem);
        System.out.println("editItem: " + selectedItem);
        editStack();
    }

    public void freeStack(){
        itemsStack.clear();
        emptyInstance();
    }

    public void removeItem() {
        T selectedItem = itemsStack.pop();
        if (selectedItem.getOwner() == null)
            selectedItem.setOwner(getCurrentOwner());
        CRUD.delete(selectedItem);
        System.out.println("editItem: " + selectedItem);
        editStack();
    }


    public abstract List<String> getFieldNames();

    public Object getFieldValue(T item, String fieldName) throws Exception {
        try {
            Field field = this.classType.getDeclaredField(fieldName);
            field.setAccessible(true); // Make private fields accessible
            return field.get(item);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}




