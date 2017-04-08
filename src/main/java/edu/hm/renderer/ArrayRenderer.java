package edu.hm.renderer;

/**
 * A renderer for arrays.
 * @author Juliane Seidl
 * @author Caro Dierenberger
 *
 */
public class ArrayRenderer {

    /**
     * Gives a String representation of the given array.
     *
     * @param array The array to render
     * @return String with representation of the array's elements
     */
    public String render(int[] array){
        String output = "[";
        for(int element : array)
            output += element + ", ";
        output += "]\n";
        return output;
    }
}
