package edu.hm.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * The class for rendering (string representation) attributes and methods of an object.
 *
 * @author Juliane Seidl
 * @author Caro Dierenberger
 */
public class Renderer {
    private Object object;

    /**
     * The constructor for a Renderer Object.
     *
     * @param object The object to render.
     */
    public Renderer(Object object){
        this.object = object;
    }

    /**
     * Gives a string representation of annotated render attributes and methods of the object.
     *
     * @return A string that represents the object's annotated attributes and methods.
     */
    public String render() {
        return renderAttributes();
    }

    /**
     * Gives a string representation of annotated render attributes of the object.
     *
     * @return A string that represents the object's annotated attributes.
     */
    private String renderAttributes(){
        // get the given objects class
        Class objectClass = object.getClass();
        // get the given objects class name
        String output = "Instance of " + objectClass.getName() + ":\n";

        // go through all fields of the given object
        try {
            Field[] fields = objectClass.getDeclaredFields();
            for(Field field : fields){
                // including private fields
                field.setAccessible(true);

                // check if field has @RenderMe annotation
                if(field.isAnnotationPresent(RenderMe.class)){

                    // get the with Parameter
                    String parameterFromWith = field.getAnnotation(RenderMe.class).with();
                    // if it has a value, continue accordingly
                    if(parameterFromWith.length() > 0){
                        // invoke the given class's render method
                        Class classToRenderSeparately = Class.forName(parameterFromWith);
                        Method renderMethod = classToRenderSeparately.getMethod("render", field.getType());
                        output += renderMethod.invoke(classToRenderSeparately.newInstance(), field.get(object));
                        return output;
                    }
                    else {
                        // get field name, type and value according to instructions
                        output += field.getName() + "(Type " + field.getType().getCanonicalName() + "):" + field.get(objectClass).toString() + "\n";
                        return output;
                    }
                }
            }
        } catch(Exception exception){
            exception.printStackTrace();
        }
        // when nothing was annotated
        return "";
    }
}


