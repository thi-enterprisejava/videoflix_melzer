package de.thi.validation;

import de.thi.videoflix.validation.ImageValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImageValidatorTest {

    ImageValidator imageValidator;

    @Before
    public void setUp() throws Exception {
        imageValidator = new ImageValidator();
    }

    @Test(expected = ValidatorException.class)
    public void thatWrongFiletypeIsRejected() throws Exception {
        Part part = mock(Part.class);
        when(part.getContentType()).thenReturn("mp3");
        imageValidator.validate(null, null, part);
    }

    @Test(expected = ValidatorException.class)
    public void thatTooLargeImageIsRejected() {
        Part part = Mockito.mock(Part.class);
        when(part.getContentType()).thenReturn("image/png");
        when(part.getSize()).thenReturn((long) 2000000);
        imageValidator.validate(null, null, part);
    }

    @Test
    public void thatCorrectImageIsValid() throws Exception {
        Part part = Mockito.mock(Part.class);
        when(part.getSize()).thenReturn((long) 1000);
        when(part.getContentType()).thenReturn("image/jpg");
        imageValidator.validate(null, null, part);
    }
}
