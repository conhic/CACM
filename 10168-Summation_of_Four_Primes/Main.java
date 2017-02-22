import java.util.*;
import java.io.*;

 /**
 * Created by Connor on 22/02/2017.
 * Accepted with time: 0.140
 */
class Main{
    public static StringBuilder output = new StringBuilder();
    public static StringBuilder caching = new StringBuilder();
    
    public static List<Integer> precalculatePrimes(int MAX, boolean[] notPrime){        
        // initially assume all integers are not prime

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= MAX; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (!notPrime[factor]) {
                for (int j = factor; factor*j <= MAX; j++) {
                    notPrime[factor*j] = true;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        // add primes
        for (int i = 2; i <= MAX; i++) {
            if (!notPrime[i]){
                primes.add(i);
            }
        }        
        
        return primes;
    }
    
    public static int reduceForGolbaut(int number){        
        if(number % 2 == 0){
            output.append("2 2 ");
            caching.append("2 2 ");
            //System.out.print("2 2 ");
            number -= 4;
        } else {
            output.append("2 3 ");
            caching.append("2 3 ");
            //System.out.print("2 3 ");
            number -= 5;
        }
        
        return number;
    }
    
    public static void findPairPrimes(int number, boolean[] notPrime, List<Integer> primes){
        int i = 0;
            
        while(i < primes.size()){
            if(notPrime[number - primes.get(i)]){
                i++;
            } else {
                break;
            }
        }
        
        if(i < primes.size()){
            output.append(primes.get(i) + " " + (number - primes.get(i)) + "\n");
            caching.append(primes.get(i) + " " + (number - primes.get(i)) + "\n");
            //System.out.println(primes.get(i) + " " + (number - primes.get(i)));
        } else {
            output.append("Impossible.\n");
            caching.append("Impossible.\n");
            //System.out.println("Impossible.");
        }
    }
    
    public static void main(String[] args) throws IOException{
        final int MAX = 10000000;
        boolean[] notPrime = new boolean[MAX + 1];
        List<Integer> primes = precalculatePrimes(MAX, notPrime);
        
        //Added caching (EXECUTION TIME WAS THE SAME)
        Map<Integer, String> cache = new HashMap<>();
                
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        
        while((line = in.readLine()) != null && !line.equals("")){
            int number = Integer.parseInt(line);
            if(cache.containsKey(number)){
                output.append(cache.get(number));
            } else {
                if(number < 8){
                    output.append("Impossible.\n");
                    caching.append("Impossible.\n");
                    //System.out.println("Impossible.");
                } else {
                    int reducedNumber = reduceForGolbaut(number);
                    findPairPrimes(reducedNumber, notPrime, primes);
                }
                
                cache.put(number, caching.toString());
                caching.setLength(0);
            }
        }
        
        System.out.printf(output.toString());
    }
}