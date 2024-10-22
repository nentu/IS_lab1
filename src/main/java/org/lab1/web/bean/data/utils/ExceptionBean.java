package org.lab1.web.bean.data.utils;

import lombok.Data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "exceptionBean")
@SessionScoped
@Data
public class ExceptionBean {
    int errorCode;
}
