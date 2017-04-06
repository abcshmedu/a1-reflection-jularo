package edu.hm.renderer;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Renderer {
    private Object object;

    public Renderer(Object object){
        this.object = object;
    }

    public String render() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        // get the given objects class
        Class objectClass = object.getClass();
        // get the given objects class name
        String output = "Instance of " + objectClass.getName() + ":\n";

        // go through all fields of the given object
        for(Field field : objectClass.getDeclaredFields()){
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
        return "";
    }
}


