import java.io.*;
import java.util.*;

/**
 * Created by Connor on 08/02/2017.
 * Accepted with time: 0.160
 *
 * Observations: BufferedReader > Scanner (Scanner is almost 0.5 seconds slower)
 */
class Main{

    public static void main(String[]args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());
        for(int i = 0; i < cases; i++){
            String[] line = in.readLine().split(" ");

            int numRelatives = Integer.parseInt(line[0]);
            int[] relatives = new int[numRelatives];
            for(int j = 0; j < numRelatives; j++){
                relatives[j] = Integer.parseInt(line[j + 1]);
            }

            Arrays.sort(relatives);
            int mediana = relatives[numRelatives / 2];
            int distance = 0;

            for(int j : relatives){
                distance += Math.abs(j - mediana);
            }

            System.out.printf("%d\n", distance);
        }
    }
}
