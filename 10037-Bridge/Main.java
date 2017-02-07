import java.io.*;
import java.util.*;

/**
 * Created by Connor on 07/02/2017.
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
	
	public static void calculate(int[] peopleTimes){
		int totalTime = 0;
		String output = "";
		
		if(peopleTimes.length <= 2){
			for(int i = 0; i < peopleTimes.length - 1; i++){
				output += peopleTimes[i] + " ";
				totalTime += peopleTimes[i];
			}
			output += peopleTimes[peopleTimes.length - 1];
			totalTime += peopleTimes[peopleTimes.length - 1];
			
		} else {
			Queue<Integer> queue = new LinkedList<>();
			queue.add(peopleTimes[0]);
			queue.add(peopleTimes[1]);
			output += peopleTimes[0] + " ";
			output += peopleTimes[1] + " ";
			totalTime += peopleTimes[0];
			totalTime += peopleTimes[1];
			
			Integer person;
			for(int i = peopleTimes.length - 1; i >= 2; i-=2){
				if((person = queue.poll()) == null){
					person = peopleTimes[0];
					queue.add(peopleTimes[1]);
					output += peopleTimes[0] + " " + peopleTimes[1] + "\n";
					totalTime += peopleTimes[0] + peopleTimes[1];
				}
				output += person + "\n";
				totalTime += person;
				
				//Cross the next 2 from end people
				output += peopleTimes[i];
				totalTime += peopleTimes[i];
				if(i - 1 >= 2){
					output += " " + peopleTimes[i - 1];
					totalTime += peopleTimes[i - 1];
				}
				output += "\n";
			}
			
			output += peopleTimes[1] + " " + peopleTimes[0];
			totalTime += peopleTimes[1] + peopleTimes[0];
		}
		
		System.out.printf("%d\n",totalTime);
		System.out.printf("%s\n",output);
		System.out.printf("\n");
	}

	public static void main(String[]args) throws IOException{
		Scanner scan = new Scanner(System.in);
		List<Integer> crossingOrder;	//List containing crossing order.
		int totalTime = 0;	//Total time to cross bridge.
		
		int cases = scan.nextInt();
		scan.nextLine();
		
		for(int i = 0; i < cases; i++){
			int people = scan.nextInt();
			int[] peopleTimes = new int[people];
			
			for(int j = 0; j < peopleTimes.length; j++){
				peopleTimes[i] = scan.nextInt();
			}			
			
			order(peopleTimes);
			calculate(peopleTimes);
			if(i != cases - 1){
				System.out.printf("\n");
			}
		}		
	}
}