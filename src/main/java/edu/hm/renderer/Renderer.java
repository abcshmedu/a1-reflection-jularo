package edu.hm.renderer;


import java.lang.reflect.Field;

public class Renderer {
    private Object object;

    public Renderer(Object object){
        this.object = object;
    }

    public String render() throws IllegalAccessException {
        // get the given objects class
        Class objectClass = object.getClass();
        // get the given objects class name
        String output = "Instance of " + objectClass.getName() + ":\n";

        // get all fields from the given objects
        for(Field field : objectClass.getDeclaredFields()){
            // including private fields
            field.setAccessible(true);

            // check if field has @RenderMe annotation
            if(field.isAnnotationPresent(RenderMe.class)){
                //todo test for with !!!!



                // get field name, type and value according to instructions
                output += field.getName() + "(Type " + field.getType().getCanonicalName() + "):" + field.get(objectClass) + "\n";


            }




        }



        return "TO DO";
    }
}


