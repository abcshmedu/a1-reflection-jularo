package main.edu.hm;
//package test.edu.hm;


import java.util.Date;

import main.edu.hm.renderer.RenderMe;

/**
 * 
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public class SomeClass {
  
    private static final  int MAGIC_NUMBER = 123456789;
    private static final  int THREE = 3;

    @RenderMe
    private int foo;

    @RenderMe(with = "main.edu.hm.renderer.ArrayRenderer")
    private int[] array = {1, 2, THREE, };
    
    @RenderMe
    private Date date = new Date(MAGIC_NUMBER);

    private String doNotRenderMeString = "Do not render me String";

    @RenderMe
    private String renderMethodString(){
        return "Hello";
    }

    @RenderMe(with= "main.edu.hm.renderer.ArrayRenderer")
    private int[] renderMethodArray(){
        return new int[] {1, 2, THREE, };
    }

    @RenderMe
    private void doNotRenderMeWithVoid(){
        System.out.println("Do not render me");
    }

    @RenderMe
    private String doNotRenderMeWithParameter(String string){
        return string;
    }

    private int doNotRenderMe(){
        return 1;
    }

    
    /**
     * Constructor of SomeClass.
     * 
     * @param foo value for foo 
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}
