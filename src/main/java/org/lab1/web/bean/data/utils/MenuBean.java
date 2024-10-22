package org.lab1.web.bean.data.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuBean {
    static String prefix = "tables/";
    static String postfix = "/table.xhtml";

    String contentName = "coordinate";

    public String goCoordinates(){
        return "coordinate";
    }

    public String goRings(){
        return "ring";
    }
    public String goCity(){
        return "magicCity";
    }
    public String goBookCreature(){
        return "bookCreature";
    }

    public String setContentName(String contentName) {
        this.contentName = contentName;
        return contentName;
    }

    public String getContentName() {
        return prefix + contentName + postfix;
    }


}
