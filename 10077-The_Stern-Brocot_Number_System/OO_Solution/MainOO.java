import java.io.*;

class MainOO{

    public static int compare(int a, int b, int c, int d){
        int first = a*d;
        int second = b*c;

        if(first == second){
            return 0;
        } else {
            return (first > second) ? 1 : -1;
        }
    }

    public static void main(String[] args) throws IOException{
        int sn, sd;     //Represents searched numerator /denominator

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
            sn = Integer.parseInt(fraction[0]);
            sd = Integer.parseInt(fraction[1]);
            Fraction target = new Fraction(sn, sd);

            int diff;
            while ((diff = root.compareTo(target)) != 0) {
                if (diff == -1) {
                    left = new Fraction(root.P, root.Q);
                    root = root.add(right);
                    sb.append("R");
                } else if (diff == 1) {
                    right = new Fraction(root.P, root.Q);
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
    public int P;
    public int Q;

    Fraction() {
        this.P = 0;
        this.Q = 1;
    }

    Fraction(int P, int Q) {
        int g = gcd(P, Q);
        P /= g;
        Q /= g;
        if (Q < 0) {
            P = -P;
            Q = -Q;
        }
        this.P = P;
        this.Q = Q;
    }

    public Fraction add(Fraction B) {
        return new Fraction(this.P + B.P, this.Q + B.Q);
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    public int compareTo(Fraction B) {
        if (this.P * B.Q == B.P * this.Q)
            return 0;
        if (this.P * B.Q < B.P * this.Q)
            return -1;
        return 1;
    }
}