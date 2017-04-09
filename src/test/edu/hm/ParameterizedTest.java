package test.edu.hm;


import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.edu.hm.SomeClass;
import main.edu.hm.renderer.Renderer;

/**
 * 
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
@RunWith(Parameterized.class)
public class ParameterizedTest {

    private String expectedResult;
    private int fooValue;
    
    private static final int RANDOM_NUMBER = 123456;
    private static final int J_BDAY = 140689;
    private static final int C_BDAY = 260890;

    /**
     * Constructor.
     *
     * @param fooValue The value of foo.
     * @param expectedResult The expected result expected.
     * @see Renderer
     */
    public ParameterizedTest(int fooValue, String expectedResult) {
        this.fooValue = fooValue;
        this.expectedResult = expectedResult;
    }

    /**
     * Test cases.
     *
     * @return The collection of tests
     */
    @Parameterized.Parameters
    public static Collection testCollection() {
        return Arrays.asList(new Object[][]{

                {RANDOM_NUMBER, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): " + RANDOM_NUMBER + "\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},

                {Integer.MAX_VALUE, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): " + Integer.MAX_VALUE + "\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},

                {Integer.MIN_VALUE, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): " + Integer.MIN_VALUE + "\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},

                {C_BDAY, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): " + C_BDAY + "\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},

                {J_BDAY, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): " + J_BDAY + "\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"}
        });
    }

    /**
     * Test method.
     *
     * @throws InstantiationException Thrown when an application tries to create an instance of a class using the newInstance method in class Class, but the specified class object cannot be instantiated.
     * @throws InvocationTargetException InvocationTargetException is a checked exception that wraps an exception thrown by an invoked method or constructor.
     * @throws IllegalArgumentException Thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * @throws IllegalAccessException An IllegalAccessException is thrown when an application tries to reflectively create an instance (other than an array), set or get a field, or invoke a method, but the currently executing method does not have access to the definition of the specified class, field, method or constructor.
     * @throws SecurityException Thrown by the security manager to indicate a security violation.
     * @throws NoSuchMethodException Thrown when a particular method cannot be found.
     * @throws ClassNotFoundException Thrown when an application tries to load in a class through its string name but no definition for the class with the specified name could be found.
     */
    @Test
    public void testRendering() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        assertEquals(new Renderer(new SomeClass(fooValue)).render(), expectedResult);
    }
}