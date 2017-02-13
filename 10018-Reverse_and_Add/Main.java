import java.io.*;

/**
 * Created by Connor on 13/02/2017.
 * Accepted with time: 0.180
 */
class Main{

    public static long reverse(long number){
        long reverse = 0;

        while( number != 0 ){
            reverse = reverse * 10;
            reverse = reverse + number%10;
            number = number/10;
        }

        return reverse;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(in.readLine());

        for(int i = 0; i < cases; i++){
            long num = Long.parseLong(in.readLine());
            long reverseNum;
            int count = 0;

            while((reverseNum = reverse(num)) != num){
                num += reverseNum;
                count++;
            }
            System.out.printf("%d %d\n", count, num);
        }
    }
}