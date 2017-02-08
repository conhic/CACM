import java.io.*;
import java.util.*;

/**
 * Created by Connor on 07/02/2017.
 * Accepted with time: 0.100
 */

class Main{

    /*
    * Uses the counting sort implementation
    */
    public static int[] order(int[] input){
        //Obtain min and max values of input array
        int max = input[0];
        int min = input[0];

        for(int i = 1; i < input.length; i++){
            if(input[i] > max){
                max = input[i];
            }

            if(input[i] < min){
                min = input[i];
            }
        }

        //Create counter for all values containted in [min, max]
        int[] counter = new int[max - min + 1];

        //Count quantity of each value contained in input
        for(int i = 0; i < input.length; i++){
            counter[input[i] - min]++;
        }

        //Recalculate quantity of each value
        for(int i = 1; i < counter.length; i++){
            counter[i] = counter[i] + counter[i - 1];
        }

        //Order array using counter previously calcuated
        int[] ordered = new int[input.length];

        for(int i = 0; i < input.length; i++){
            ordered[counter[input[i] - min] - 1] = input[i];
            counter[input[i] - min]--;
        }

        //return ordered array
        return ordered;
    }

    /*
    * Calculate crossing order selecting the optimal algorithm
    */
    public static void calculate(int[] peopleTimes){
        int totalTime = 0;
        String output = "";

        if(peopleTimes.length <= 3){
            if(peopleTimes.length == 1){
                output += peopleTimes[0];
                totalTime += peopleTimes[0];
            } else if (peopleTimes.length == 2){
                output += peopleTimes[0] + " " + peopleTimes[1];
                totalTime += peopleTimes[1];
            } else {
                output += peopleTimes[0] + " " + peopleTimes[1] + "\n" +
                        peopleTimes[0] + "\n" + peopleTimes[0] + " " + peopleTimes[2];
                totalTime += peopleTimes[1] + peopleTimes[0] + peopleTimes[2];
            }
        } else {
            int fastOne = peopleTimes[0];
            int fastTwo = peopleTimes[1];
            int i = 0;

            for(; peopleTimes.length - 1 - i > 2; i+=2){ //Ultimos 3 o menos siempre se computan igual
                int slowOne = peopleTimes[peopleTimes.length - i - 1]; //Tail (Slowest)
                int slowTwo = peopleTimes[peopleTimes.length - i - 2]; //Tail - 1

                //Decide which algorithm to use
                //A B -> A -> Z Y -> B
                //Fastest two cross, fastest comes back, slowest 2 go, seconds fastest comes back.
                int algorithmOne = fastTwo + fastOne + slowOne + fastTwo;

                //A Z -> A -> A Y -> A
                //Fastest and slowest cross, fast comes back, fastest and second slowest cross, fastest comes back.
                int algorithmTwo = slowOne + fastOne + slowTwo + fastOne;

                if(algorithmOne > algorithmTwo){
                    totalTime += algorithmTwo;
                    output += 	fastOne + " " + slowOne + "\n" +
                            fastOne + "\n" +
                            fastOne + " " + slowTwo + "\n" +
                            fastOne + "\n";
                } else {
                    totalTime += algorithmOne;
                    output +=	fastOne + " " + fastTwo + "\n" +
                            fastOne + "\n" +
                            slowTwo + " " + slowOne + "\n" +
                            fastTwo + "\n";
                }
            }

            if(peopleTimes.length - i - 3 == 0){
                output += peopleTimes[0] + " " + peopleTimes[1] + "\n" +
                        peopleTimes[0] + "\n" + peopleTimes[0] + " " + peopleTimes[2];
                totalTime += peopleTimes[1] + peopleTimes[0] + peopleTimes[2];
            } else if(peopleTimes.length - i - 2 == 0){
                output += fastOne + " " + fastTwo;
                totalTime += fastTwo;
            } else {
                output += fastOne;
                totalTime += fastOne;
            }
        }

        System.out.printf("%d\n",totalTime);
        System.out.printf("%s\n",output);
    }

    public static void main(String[]args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
        int cases = Integer.parseInt(in.readLine());

        for(int i = 0; i < cases; i++){
			in.readLine();
            int people = Integer.parseInt(in.readLine());
            int[] peopleTimes = new int[people];

            for(int j = 0; j < peopleTimes.length; j++){
                peopleTimes[j] = Integer.parseInt(in.readLine());
            }

            peopleTimes = order(peopleTimes);
            calculate(peopleTimes);
            if(i < cases - 1){
                System.out.printf("\n");
            }
        }
    }
}
