package de.thi.videoflix.web.model;

import de.thi.videoflix.domain.User;
import de.thi.videoflix.services.UserService;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@ViewScoped
public class RegisterUser implements Serializable {

    private User user;
    @Inject
    UserService userService;

    @PostConstruct
    public void postConstruct() {
        System.out.println("New user");
        this.user = new User();
    }

    public String doAddUser() throws NoSuchAlgorithmException {
        this.user.setRole(User.UserRole.USER);
        userService.addUser(this.user);
        return "login.xhtml";
    }

    public void doLogout() throws Exception {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession();
        externalContext.setResponseStatus(401);
        externalContext.getResponseOutputWriter().write("<html><head><meta http-equiv='refresh' content='0;index.xhtml'></head></html>");
        facesContext.responseComplete();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
