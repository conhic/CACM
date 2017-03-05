import java.io.*;

/**
 * Created by Connor on 07/02/2017.
 * Accepted with time: 0.340
 */
class Main{
    
    public static void precalculatePrimes(boolean[] notPrime){   
        // initially assume all integers prime
        // Set 0 and 1 to notPrime
        notPrime[0] = notPrime[1] = true;

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor < notPrime.length; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (!notPrime[factor]) {
                for (int j = factor; factor*j < notPrime.length; j++) {
                    notPrime[factor*j] = true;
                }
            }
        }
    }
    
    //https://en.wikipedia.org/wiki/Modular_exponentiation#Right-to-left_binary_method
    public static boolean fermat(long base, long exponent, long modulus){
        long temp = base;
        long result = 1;
        while(exponent > 0){
            if(exponent % 2 == 1){
                result = (result * base) % modulus;
            }
            exponent /= 2;
            base = (base * base) % modulus;
        }
    
        return result == temp;
    }
    
    public static boolean isCarmichael(long number){
        for(int i = 2; i < number; i++){
            if(!fermat(i, number, number)){
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) throws IOException{
        final int MAX= 65000;
        boolean[] notPrime = new boolean[MAX + 1];
        
        precalculatePrimes(notPrime);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer output = new StringBuffer();
        
        String line;
        while(!((line = in.readLine()).equals("0"))){
            int number = Integer.parseInt(line);
            if(notPrime[number] && fermat(2,number,number)){//isCarmichael(number)){
                output.append("The number " + number + " is a Carmichael number.\n");
            } else {
                output.append(number + " is normal.\n");
            }
        }
        
        System.out.printf(output.toString());
    }
}