package org.lab1.web.bean.data.abstracts;

import org.lab1.data.CRUD;
import org.lab1.data.entity.BookCreature;
import org.lab1.data.entity.Ownerable;
import org.lab1.web.bean.data.BookCreatureBean;
import org.lab1.web.bean.data.Identable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.Map;

public abstract class UsedManagerBean<T extends Ownerable & Identable> extends ManagerBean<T> {
    public UsedManagerBean(Class<T> classType, String editPanelName, String componentDialogName) {
        super(classType, editPanelName, componentDialogName);
    }

    @Override
    public void removeItem() {
        T selectedItem = super.itemsStack.pop();

        try {
            CRUD.delete(selectedItem);
            System.out.println("editItem: " + selectedItem);
        } catch (Exception e) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            T searchedItem = CRUD.find(super.classType, selectedItem.getId());
            if (searchedItem == null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item was removed", null));
            } else {
                List<BookCreature> editList = CRUD.findBookCreatureByClassId(classType, selectedItem.getId());

                BookCreatureBean viewScopedBean = getBookCreatureBean();

                viewScopedBean.getItemsStack().addAll(editList);
                finishEditItem();
            }
        }
    }

    public static BookCreatureBean getBookCreatureBean() {
        Map<String, Object> session =  FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (BookCreatureBean) session.get("bookCreatureBean");
    }

    public void finishEditItem() {
        System.out.println("finishEditItem");
        BookCreatureBean bookBean = getBookCreatureBean();
        bookBean.editStack();
    }
}
