package main.edu.hm.renderer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

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
        if (object == null) {
            throw new NullPointerException();
        }
        this.object = object;
    }

    /**
     * Gives a string representation of annotated render fields and methods of the object.
     *
     * @return A string that represents the object's annotated fields and methods.
     * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
     * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     * @throws SecurityException Thrown by the security manager to indicate a security violation.
     * @throws NoSuchMethodException Thrown when a particular method cannot be found.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name but no definition for the class with the specified name could be found.
     */
    public String render() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
      // get the given objects class
        Class< ? extends Object> objectClass = object.getClass();

        builder.append("Instance of " + object.getClass().getName() + ":\n");
        renderFields(objectClass.getDeclaredFields());
        renderMethods(objectClass.getDeclaredMethods());

        return builder.toString();
    }

    /**
     * Gives a string representation of annotated render fields of the object.
     *
     * @param fields the class to render 
     * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
     * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     * @throws SecurityException Thrown by the security manager to indicate a security violation.
     * @throws NoSuchMethodException Thrown when a particular method cannot be found.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name but no definition for the class with the specified name could be found.
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
     * Gives a string representation of annotated render methods of the object.
     * 
     * @param methods the class to render
     * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name but no definition for the class with the specified name could be found.
     * @throws NoSuchMethodException Thrown when a particular method cannot be found.
     * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
     */
    private void renderMethods(Method[] methods) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        // sort the Methods array alphabetical for stable test results
        Arrays.sort(methods, new Comparator<Method>() {
            public int compare(Method o1, Method o2) {
                return o1.toString().compareToIgnoreCase(o2.toString());
            } 
          });
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(RenderMe.class) && method.getParameterTypes().length == 0 && !method.getReturnType().toString().equals("void")) {
                String parameterFromWith = method.getAnnotation(RenderMe.class).with();
                if (parameterFromWith.length() > 0) {
                    Class classToRenderSeparately = Class.forName(parameterFromWith);
                    Method renderMethod = classToRenderSeparately.getMethod("render", method.getReturnType());
                    builder.append(method.getName() + " (Type " + method.getReturnType().getCanonicalName() + "): " + renderMethod.invoke(classToRenderSeparately.newInstance(), method.invoke(object, null)));
                }
                else {
                    builder.append(method.getName() + " (Type " + method.getReturnType().getCanonicalName() + "): " + method.invoke(object, null).toString() + "\n");
                }
            }
        }
    }
}


