package de.thi.videoflix.validation;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.Part;


@RequestScoped
@Named
public class ImageValidator implements Validator {

    private static long MAX_FILESIZE = 1024 * 1024;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value){

        Part file = (Part) value;

        if (file != null) {
            if (!file.getContentType().matches("^image/.*$")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "File has to be an image", "File has to be an image"));
            }

            if (file.getSize() > MAX_FILESIZE) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Filesize is too big", "Filesize is too big"));
            }
        }
    }
}
