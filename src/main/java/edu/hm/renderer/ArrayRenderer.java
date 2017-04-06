package edu.hm.renderer;


public class ArrayRenderer {

    public String render(int[] array){
        String output = "[";
        for(int element : array)
            output += element + ", ";
        output += "]";
        return output;
    }
}
