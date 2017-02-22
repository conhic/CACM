import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Created by Connor on 22/02/2017.
 * Accepted with time: 0.080
 */
class Main{
    public static int[] cache = new int[1000001];
    
    public static int threeNPlusOne(int start, int end){
        int max = -1;
        
        for(int i = start; i <= end; i++){
            if(cache[i] > 0){
                if(cache[i] > max){
                    max = cache[i];
                }
            } else {     
                int counter = 1;
                long n = (long)i;
                while(n != 1){
                    if(n % 2 == 1){
                        n = 3 * n + 1;
                        counter++;
                    } else {
                        n /= 2;
                        counter++;
                    }
                }
                
                cache[i] = counter;
            }
            
            if(cache[i] > max){
                max = cache[i];    
            }            
        }
        return max;
    }
    
    public static void main(String[] args) throws IOException{
        StringBuffer output = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while((line = in.readLine()) != null && !line.equals("")){
            String[] numbers = line.split("\\D+");
            int i = 0;
            if(numbers[0].equals("")){
                i++;
            } 
            
            int start = Integer.parseInt(numbers[i++]);
            int end = Integer.parseInt(numbers[i++]);
            
            int maxIndex = threeNPlusOne(Math.min(start, end), Math.max(start, end));
            output.append(start + " " + end + " " + maxIndex + "\n");
        }
        System.out.printf(output.toString());
    }
}