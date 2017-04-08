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

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private String expectedResult;

    private int fooValue;

    /**
     * Constructor
     *
     * @param fooValue       The value of foo.
     * @param expectedResult The expected result expected
     * @see Renderer
     */
    public ParameterizedTest(int fooValue, String expectedResult) {
        this.fooValue = fooValue;
        this.expectedResult = expectedResult;
    }

    /**
     * Test cases
     *
     * @return The collection of tests
     */
    @Parameterized.Parameters
    public static Collection testCollection() {
        return Arrays.asList(new Object[][]{

                {123456, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): 123456\n"
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

                {260890, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): 260890\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"},

                {140689, "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): 140689\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"}
        });
    }

    /**
     * Test method
     *
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRendering() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        assertEquals(new Renderer(new SomeClass(fooValue)).render(), expectedResult);
    }
}