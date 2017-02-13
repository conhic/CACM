import java.util.*;
import java.io.*;

/**
 * Created by Connor on 07/02/2017.
 * Accepted with time: 0.130
 */
class Main{
	
	public static int calculateCarry(int a, int b){
		int count = 0;
		int carry = 0;
		
		while(a != 0 || b != 0){
			carry = carry + a % 10 + b % 10;
			if(carry >= 10){
				count++;
			}
			
			carry /= 10;
			a /= 10;
			b /= 10;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		String line = "";
		
		while(!((line = in.readLine()).equals("0 0"))){
			String[] input = line.split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			int carry = calculateCarry(a, b);
			if(carry <= 1){	//0 and 1 have unique outputs
				if(carry == 0){
					output.append("No carry operation.\n");
				} else {
					output.append("1 carry operation.\n");
				}
			} else {
				output.append(carry + " carry operations.\n");
			}
		}
		System.out.printf(output.toString());
	}	
}
