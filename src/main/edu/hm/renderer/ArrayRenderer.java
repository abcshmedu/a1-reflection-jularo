package main.edu.hm.renderer;

/**
 * A renderer for arrays.
 *
 * @author Juliane Seidl
 * @author Caro Direnberger
 */
public class ArrayRenderer {

    /**
     * Gives a String representation of the given array.
     *
     * @param array The array to render
     * @return String with representation of the array's elements
     */
    public String render(int[] array) {

        StringBuilder output = new StringBuilder();

        output.append("[");
        for (int element : array) {
            output.append(element + ", ");
        }
        output.append("]\n");

        return output.toString();
    }
}
