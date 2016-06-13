package de.thi.videoflix.util;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Dependent
public class Resources {
    @Produces
    public Logger produceLog(){
        return Logger.getLogger("MyLogger", "messages");
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @PersistenceContext
    private EntityManager em;
}
