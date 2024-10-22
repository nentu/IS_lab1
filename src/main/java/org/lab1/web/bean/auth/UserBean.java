package org.lab1.web.bean.auth;

import lombok.Data;
import org.lab1.data.CRUD;
import org.lab1.data.entity.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "userBean")
@SessionScoped
@Data
public class UserBean {

    private Long id = -1L;
    private String login;
    private String password;
    private String nick;
    private boolean isAdmin = false;
    private boolean isRequest = true;
    private String salt;


    public boolean validateUser() {
        User probUser = CRUD.getUserByLogin(login);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        String errorMsg = "";

        if (probUser == null)
            errorMsg = "Invalid Login";
        else {
            boolean cor = probUser.getPassword().equals(User.PAPER + password + probUser.getSalt());
            if (!cor)
                errorMsg = "Invalid password";
            else if (probUser.isRequest())
                errorMsg = "Wait. Your request is still in processed";
        }


        if (!errorMsg.isEmpty()) {
            facesContext.addMessage(":loginForm:loginPanel", new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg, null));
            return false;
        }


        id = probUser.getId();
        isAdmin = probUser.isAdmin();
        nick = probUser.getNick();
        salt = probUser.getSalt();
        return true;
    }

    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setNick(nick);
        user.setAdmin(isAdmin);
        user.setSalt(salt);

        return user;
    }

    public int logout() {
        id = -1L;
        return 1;
    }

}
