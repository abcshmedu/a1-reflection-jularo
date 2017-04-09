package main.edu.hm;

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

    
    /**
     * Constructor of SomeClass.
     * 
     * @param foo value for foo 
     */
    public SomeClass(int foo) {
        this.foo = foo;
    }
}
