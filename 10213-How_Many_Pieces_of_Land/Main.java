import java.io.*;
import java.math.BigInteger;

/**
 * Created by Connor on 15/02/2017.
 * Accepted with time: 0.140
 */
class Main{
    
    public static BigInteger calculateLand(BigInteger n){
        return ((n.pow(4)).subtract((n.pow(3)).multiply(BigInteger.valueOf(6)))
                .add((n.pow(2)).multiply(BigInteger.valueOf(23)))
                .subtract(n.multiply(BigInteger.valueOf(18)))
                .add(BigInteger.valueOf(24)))
                .divide(BigInteger.valueOf(24));
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        
        int cases = Integer.parseInt(in.readLine());
        for(int i = 0; i < cases; i++){
            BigInteger num = new BigInteger(in.readLine());
            output.append(calculateLand(num) + "\n");
        }
        
        System.out.printf(output.toString());
    }
}