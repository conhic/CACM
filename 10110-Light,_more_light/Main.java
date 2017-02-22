import java.io.*;
import java.lang.*;

 /**
 * Created by Connor on 22/02/2017.
 * Accepted with time: 0.130
 */
class Main{
    
    public static boolean isPerfectSquare(long num){
        double squareWithDecimals = Math.sqrt(num);
        int sqareFloored = (int)squareWithDecimals;
        return (squareWithDecimals == sqareFloored);
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer output = new StringBuffer();
        String line;
        
        while(!((line = in.readLine()).equals("0"))){
            long num = Long.parseLong(line);
            if(isPerfectSquare(num)){
                output.append("yes\n");
            } else {
                output.append("no\n");
            }
        }        
        System.out.printf(output.toString());
    }
}