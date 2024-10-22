package org.lab1.web.bean.data.abstracts;

import org.lab1.data.CRUD;
import org.lab1.data.entity.BookCreature;
import org.lab1.data.entity.Ownerable;
import org.lab1.web.bean.data.BookCreatureBean;
import org.lab1.web.bean.data.Identable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class UsedManagerBean<T extends Ownerable & Identable> extends ManagerBean<T> {
    public UsedManagerBean(Class<T> classType, String name) {
        super(classType, name);
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

                if (!editList.isEmpty()) {
                    BookCreatureBean viewScopedBean = getBookCreatureBean();
                    viewScopedBean.getItemsStack().addAll(editList);
                    finishEditItem();
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        externalContext.redirect(externalContext.getRequestContextPath() + "/views/data/pages/bookCreature/main.xhtml");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }


            }
        }
    }

    public static BookCreatureBean getBookCreatureBean() {
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return (BookCreatureBean) session.get("bookCreatureBean");
    }

    public void finishEditItem() {
        System.out.println("finishEditItem");
        BookCreatureBean bookBean = getBookCreatureBean();
        bookBean.editStack();
    }
}
