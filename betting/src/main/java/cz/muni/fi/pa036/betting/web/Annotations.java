package cz.muni.fi.pa036.betting.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Martin Jelínek
 */
public class Annotations {
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface DoesNotRequireLogin {
        
    }
    
}
