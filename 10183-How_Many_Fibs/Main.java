import java.io.*;
import java.util.*;
import java.math.BigInteger;

/**
 * Created by Connor on 15/02/2017.
 * Accepted with time: 0.050
 */
class Main{

    public static void precalculateFib(BigInteger[] fibs){
        fibs[0] = fibs[1] = BigInteger.ONE;

        for(int i = 2; i < fibs.length; i++){
            fibs[i] = fibs[i-1].add(fibs[i-2]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        String line;

        BigInteger[] fibs = new BigInteger[480];
        precalculateFib(fibs);
        BigInteger beginning, end;

        while(!((line = in.readLine()).equals("0 0"))){
            String[] limits = line.split(" ");
            beginning = new BigInteger(limits[0]);
            end = new BigInteger(limits[1]);
            int count = 0;
            
            int startIndex = Arrays.binarySearch(fibs, 1, fibs.length, beginning);
            int endIndex = Arrays.binarySearch(fibs, 1, fibs.length, end);

            if((startIndex == endIndex) && startIndex < 0){
                output.append("0\n");
            } else {
                if( startIndex < 0){
                    startIndex = ((startIndex + 1) * -1);

                }

                if( endIndex < 0){
                    endIndex = ((endIndex + 2) * -1);
                }

                output.append(endIndex - startIndex + 1 + "\n");
            }
        }

        System.out.printf(output.toString());
    }
}
