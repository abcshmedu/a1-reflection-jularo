package edu.hm.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The class for rendering (string representation) attributes and methods of an object.
 *
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public class Renderer {
	
    private Object object;
    private StringBuilder builder = new StringBuilder();

    /**
     * The constructor for a Renderer Object.
     *
     * @param object The object to render.
     */
    public Renderer(Object object) {
    	if(object==null) throw	new NullPointerException();
        this.object = object;
    }

    /**
     * Gives a string representation of annotated render attributes and methods of the object.
     *
     * @return A string that represents the object's annotated fields and methods.
     * @throws ClassNotFoundException 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InstantiationException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public String render() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {// get the given objects class
        Class objectClass = object.getClass();
        renderFields(objectClass);
        renderMethods(objectClass);
        
        return builder.toString();
    }

    /**
     * Gives a string representation of annotated render fields of the object.
     *
     * @param toRender the class to render
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws ClassNotFoundException
     */
    private void renderFields(Class toRender) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException  {
    	 final Field[] fields = toRender.getDeclaredFields();
    	 
    	 for (final Field field : fields) {
    		 field.setAccessible(true);
    		 if (field.isAnnotationPresent(RenderMe.class)) {
    			 String parameterFromWith = field.getAnnotation(RenderMe.class).with();
    			 if (parameterFromWith.length() > 0) {
    				 Class classToRenderSeparately = Class.forName(parameterFromWith);
                     Method renderMethod = classToRenderSeparately.getMethod("render", field.getType());
                     builder.append(renderMethod.invoke(classToRenderSeparately.newInstance(), field.get(object)));
    			 }
    			 else{
    				 builder.append(field.getName() + "(Type " + field.getType().getCanonicalName() + "):" + field.get(toRender).toString() + "\n");
    			 }
    		 }
    	 }
		
	}

    /**
     *
     * @param toRender the class to render
     */
    private void renderMethods(Class toRender) {
    	Method[] methods = toRender.getDeclaredMethods();
    	for (Method method : methods) {
    		method.setAccessible(true);
            if (method.isAnnotationPresent(RenderMe.class)) {
                if (!method.getReturnType().toString().equals("void")) {
                    builder.append(method.getName());
                    builder.append("returnType: " + method.getReturnType());
                }
            }
        }
    }

    
}


