package test.edu.hm;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.edu.hm.SomeClass;
import main.edu.hm.renderer.Renderer;

public class RendererTest {
    private SomeClass toRender;
    private Renderer renderer;

    @Before
    public void setUp() {
        toRender = new SomeClass(5);
        renderer = new Renderer(toRender);
    }

    @Test
    public void testRendering() throws Exception {
        assertEquals(
                "Instance of main.edu.hm.SomeClass:\n"
                        + "foo (Type int): 5\n"
                        + "array (Type int[]): [1, 2, 3, ]\n"
                        + "date (Type java.util.Date): Fri Jan 02 11:17:36 CET 1970\n",
                renderer.render());
    }
}