package edu.hm.renderer;

/**
 * An annotation for representing attributes of an object.
 *
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public @interface RenderMe {

    /**
     * The annotations parameter.
     *
     * @return A string with the representation of the annotated attributes of a class.
     */
    String with() default "";
}
