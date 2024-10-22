package org.lab1.web.bean.data.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuBean {
    static String prefix = "tables/";
    static String postfix = "/table.xhtml";

    String contentName = "bookCreature";

    public String getContentName() {
        return prefix + contentName + postfix;
    }


}
