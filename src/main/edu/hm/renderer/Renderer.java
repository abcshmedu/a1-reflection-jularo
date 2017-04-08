package main.edu.hm.renderer;

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
    private StringBuilder builder = builder = new StringBuilder();
    ;

    /**
     * The constructor for a Renderer Object.
     *
     * @param object The object to render.
     */
    public Renderer(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
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
        Class<? extends Object> objectClass = object.getClass();

        builder.append("Instance of " + object.getClass().getName() + ":\n");
        renderFields(objectClass.getDeclaredFields());
        renderMethods(objectClass.getDeclaredMethods());

        return builder.toString();
    }

    /**
     * Gives a string representation of annotated render fields of the object.
     *
     * @param fields the class to render
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws ClassNotFoundException
     */
    private void renderFields(Field[] fields) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

        for (final Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(RenderMe.class)) {
                String parameterFromWith = field.getAnnotation(RenderMe.class).with();
                if (parameterFromWith.length() > 0) {
                    Class classToRenderSeparately = Class.forName(parameterFromWith);
                    Method renderMethod = classToRenderSeparately.getMethod("render", field.getType());
                    builder.append(field.getName() + " (Type " + field.getType().getCanonicalName() + "): " + renderMethod.invoke(classToRenderSeparately.newInstance(), field.get(object)));
                } else {
                    builder.append(field.getName() + " (Type " + field.getType().getCanonicalName() + "): " + field.get(object).toString() + "\n");
                }
            }
        }
    }

    /**
     * @param methods the class to render
     */
    private void renderMethods(Method[] methods) {
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


