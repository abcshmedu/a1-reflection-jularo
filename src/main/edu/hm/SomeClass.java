package main.edu.hm;

import main.edu.hm.renderer.RenderMe;

import java.util.Date;

/**
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public class SomeClass {

    private static final int MAGIC_NUMBER = 123456789;
    private static final int THREE = 3;

    @RenderMe
    private int foo;

    @RenderMe(with = "main.edu.hm.renderer.ArrayRenderer")
    private int[] array = {1, 2, THREE, };

    @RenderMe
    private Date date = new Date(MAGIC_NUMBER);

    private String doNotRenderMeString = "Do not render me String";

    /**
     * Constructor of SomeClass.
     *
     * @param foo value for foo
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }

    /**
     * @return A String: "Hello"
     */
    @RenderMe
    private String renderMethodString() {
        return "Hello";
    }

    /**
     * @return new int-Array
     */
    @RenderMe(with = "main.edu.hm.renderer.ArrayRenderer")
    private int[] renderMethodArray() {
        return new int[]{1, 2, THREE, };
    }

    /**
     * Prints a string.
     */
    @RenderMe
    private void doNotRenderMeWithVoid() {
        System.out.println("Do not render me");
    }

    /**
     * @param string given string
     * @return given string
     */
    @RenderMe
    private String doNotRenderMeWithParameter(String string) {
        return string;
    }

    /**
     * @return 1
     */
    private int doNotRenderMe() {
        return 1;
    }
}
