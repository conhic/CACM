import java.io.*;

/**
 * Created by Connor on 13/02/2017.
 * Accepted with time: 0.040
 */
class Main{
	
	public static int calculate(long number){
		long n = number;
		int count = 0;
		
		while(number != 0){
			if(number % 10 == 1){
				number /= 10;
				count++;
			} else {
				number += n;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = in.readLine()) != null){
			long number = Long.parseLong(line);
			System.out.println(calculate(number));
		}
	}	
}

