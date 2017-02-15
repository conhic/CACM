import java.io.*;
import java.math.BigInteger;

/**
 * Created by Connor on 15/02/2017.
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

        BigInteger[] fibs = new BigInteger[600];
        precalculateFib(fibs);
        BigInteger beginning, end;

        while(!((line = in.readLine()).equals("0 0"))){
            String[] limits = line.split(" ");
            beginning = new BigInteger(limits[0]);
            end = new BigInteger(limits[1]);
            int count = 0;

            
            for(int i = 1; i < fibs.length; i++){
                if(fibs[i].compareTo(beginning) >= 0 && fibs[i].compareTo(end) <= 0){
                    count++;
                }
            }
            
            while(!found){
                int minComp;
                minComp = beginning.compareTo(fibs[min]);
                if(beginning.compareTo(fibs[min]) == 0 || (minComp < 0 && beginning.compareTo[min+1] > 0){
                    found = true;
                }
            }
            

            output.append(count + "\n");
        }

        System.out.printf(output.toString());
    }
}
