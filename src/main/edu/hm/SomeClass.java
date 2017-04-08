package main.edu.hm;

import java.util.Date;

import main.edu.hm.renderer.RenderMe;

public class SomeClass {
   
    @RenderMe
    private int foo;
    
    @RenderMe(with = "main.edu.hm.renderer.ArrayRenderer")
    int[] array = {1, 2, 3,};
    
    @RenderMe
    private Date date = new Date(123456789);

    
    public SomeClass(int foo) {
        this.foo = foo;
    }
}
