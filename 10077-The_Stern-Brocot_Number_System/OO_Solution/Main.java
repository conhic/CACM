import java.io.*;
/**
 * Created by Connor on 14/02/08
 * Accepted with time: 0.180
 */
class Main{

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!((line = in.readLine()).equals("1 1"))){
            //Set initial fractions to correct values
            Fraction left = new Fraction(0, 1);
            Fraction right = new Fraction(1, 0);
            Fraction root = new Fraction(1, 1);

            //Set fraction we are searching for
            String[] fraction = line.split(" ");
            int sn = Integer.parseInt(fraction[0]);
            int sd = Integer.parseInt(fraction[1]);
            Fraction target = new Fraction(sn, sd);

            int diff;
            while ((diff = root.compareTo(target)) != 0){
                if (diff == -1){
                    left = new Fraction(root.n, root.d);
                    root = root.add(right);
                    sb.append("R");
                } else if (diff == 1){
                    right = new Fraction(root.n, root.d);
                    root = root.add(left);
                    sb.append("L");
                }
            }
            sb.append("\n");
        }
        System.out.printf(sb.toString());
    }
}

class Fraction{
    int n;
    int d;

    Fraction(int n, int d) {
        this.n = n;
        this.d = d;
    }

    public Fraction add(Fraction fraction) {
        return new Fraction(this.n + fraction.n, this.d + fraction.d);
    }

    public int compareTo(Fraction fraction) {
        int first = this.n * fraction.d;
        int second = this.d * fraction.n;

        if(first == second){
            return 0;
        } else {
            return (first > second) ? 1 : -1;
        }
    }
}
