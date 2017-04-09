package edu.hm;     //<- intelliJ
//package test.edu.hm;  //<- eclipse

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.edu.hm.SomeClass;
import main.edu.hm.renderer.Renderer;

/**
 * 
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;
    
    private static final int FIVE = 5;

    /**
     * Init for RendererTest.
     */
    @Before
    public void setUp() {
        toRender = new SomeClass(FIVE);
        renderer = new Renderer(toRender);
    }

  
    /**
     * @throws Exception Could throw ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
     */
    @Test
    public void testRendering() throws Exception {
        assertEquals(
                "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): 5\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n"
                        + "renderMethodArray (Type int[]): [1, 2, 3, ]\n"
                        + "renderMethodString (Type java.lang.String): Hello\n",
                renderer.render());
    }
}