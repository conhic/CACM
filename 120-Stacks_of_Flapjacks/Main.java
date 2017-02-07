/**
 * Created by Connor on 06/02/2017.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.IOException;

class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        //TODO: Change array for linkedList
        while((line = in.readLine()) != null){
            Scanner scanner = new Scanner(line);
            System.out.printf(line + "\n");

            int[] pancakes = new int[line.split(" ").length];

            //Populate pancake stack reversed to make computation logic easier
            for(int i = pancakes.length - 1; i >= 0; i--){
                int diameter = scanner.nextInt();
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
        String output = ""; 	//Initialize with no moves
        int begin = 0; 			//Indicates current number of correct elements
		int end = stack.length - 1;

        while(!isSorted(stack)){
            int flipIndex = maxInSubset(stack, begin);

            //If the maximum value is in the top position of the stack,
            //after flipping it will be in the correct position
			if(flipIndex == end){
				//Flip max from top into correct position
				flip(stack, begin);
                output += (begin + 1) + " ";
                begin++;
            } else if(flipIndex == begin){
                begin++;
            } else {
				//Flip max to top
                flip(stack, flipIndex);
                output += (flipIndex + 1) + " ";
				
				//Flip max from top into correct position
                flip(stack, begin);
				output += (begin + 1) + " ";
                begin++;
            }
        }

        //Last instruction is always 0
        output += "0\n";
        return output;
    }

    /*
    * Reverse subset of array. Subset = [0, stack.length - max]
    */
    public static void flip(int[] stack, int beginIndex){
        for(int i = 0; i < (stack.length - beginIndex) / 2; i++){
            int temp = stack[i + beginIndex];
            stack[i + beginIndex] = stack[stack.length - 1 - i];
            stack[stack.length - 1 - i] = temp;
        }
    }

    /*
    * Returns the index of the maximum value in subset
    */
    public static int maxInSubset(int[] stack, int beginIndex){
        int maximum = -1;
        int index = -1;

        for(int i = beginIndex; i < stack.length; i++){
            if(stack[i] > maximum){
                maximum = stack[i];
                index = i;
            }
        }

        return index;
    }

    /*
    * Return true is stack is ordered from highest to lowest
    */
    public static boolean isSorted(int[] stack){
        boolean sorted = true;
        if(stack.length == 1) return sorted;
        for(int i = 0; i < stack.length - 1; i++){
            if(stack[i] < stack[i+1]){
                sorted = false;
                break;
            }
        }

        return sorted;
    }
}
