package main.edu.hm.renderer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for representing attributes of an object.
 *
 * @author Juliane Seidl
 * @author Caro Direnberger
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface RenderMe {

    /**
     * The annotations parameter.
     *
     * @return A string with the representation of the annotated attributes of a class.
     */
    String with() default "";
}
