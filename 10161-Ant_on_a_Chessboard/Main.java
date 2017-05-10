import java.math.*;
import java.io.*;

/**
 * Created by Connor on 10/05/2017.
 * Accepted with time: 0.040
 */
public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer output = new StringBuffer();
        String line;
        
        while(!((line = reader.readLine()).equals("0"))){
            int number = Integer.parseInt(line);
            int x, y;
            int swap = 0;
            
            int square = (int)Math.ceil(Math.sqrt(number));
            int distanceFromPrevSquare = number - (square - 1) * (square - 1);

            if(distanceFromPrevSquare > square){
                distanceFromPrevSquare = square - (distanceFromPrevSquare - square);
                swap = 1;
            }
            
            if(square % 2 == swap){                                                    
                x = distanceFromPrevSquare;
                y = square; 
            } else {
                x = square;
                y = distanceFromPrevSquare;
            }
            
            output.append(x + " " + y + "\n");
        }
        
        System.out.printf(output.toString());
    }
}