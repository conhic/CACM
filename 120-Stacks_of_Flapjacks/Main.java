/**
 * Created by Connor on 06/02/2017.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        //TODO: Change array for linkedList
        while((line = in.readLine()) != null){
            StringTokenizer st = new StringTokenizer(line);
            System.out.printf(line + "\n");

            int[] pancakes = new int[st.countTokens()];

            //Populate pancake stack
            for(int i = 0; i < pancakes.length; i++){
                int diameter = Integer.parseInt(st.nextToken().trim());
                pancakes[i] = diameter;
            }

            String output = order(pancakes);
            System.out.printf(output);
        }
    }

    /*
    * Returns a String containing the instructions used to order the array
    */
    public static String order(int[] stack){
        String output = ""; //Indicated no moves are needed.
        int sortedElements = 0; //Indicates current number of correct elements

        while(!isSorted(stack)){
            int flipIndex = maxInSubset(stack, sortedElements);

            //If the maximum value is in the top position of the stack,
            //after flipping it will be in the correct position
            if(flipIndex == stack.length - 1){
                sortedElements++;
            }else if(flipIndex == 0){
                output += (sortedElements + 1) + " ";
                flip(stack, sortedElements);
                sortedElements++;
            } else {
                flip(stack, flipIndex);
                output += (flipIndex + 1) + " ";
            }
        }

        //Last instruction is always 0
        output += "0\n";

        return output;
    }

    /*
    * Reverse subset of array. Subset = [0, stack.length - index]
    */
    public static void flip(int[] stack, int index){
        for(int i = 0; i < (stack.length - index) / 2; i++){
            int temp = stack[i];
            stack[i] = stack[stack.length - 1 - index - i];
            stack[stack.length - 1 -index - i] = temp;
        }
    }

    /*
    * Returns the index of the maximum value in subset
    */
    public static int maxInSubset(int[] stack, int position){
        int maximum = -1;
        int index = -1;

        for(int i = 0; i < stack.length - position; i++){
            if(stack[i] > maximum){
                maximum = stack[i];
                index = i;
            }
        }

        return index;
    }

    /*
    * Return true is stack is ordered from lowest to highest
    */
    public static boolean isSorted(int[] stack){
        boolean sorted = true;
        if(stack.length == 1) return sorted;
        for(int i = 0; i < stack.length - 1; i++){
            if(stack[i] > stack[i+1]){
                sorted = false;
                break;
            }
        }

        return sorted;
    }
}
