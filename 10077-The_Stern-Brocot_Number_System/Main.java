import java.io.*;

/**
 * Created by Connor on 14/02/2017.
 * Accepted with time: 0.080 
 */
class Main{

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
        int ln, ld, mn, md, rn, rd;  //Represent Left Middle Right numerator / denominator
        int sn, sd;                  //Represents searched numerator /denominator

        //StringBuilder sb = new StringBuilder();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!((line = in.readLine()).equals("1 1"))){
            //Set initial fractions to correct values
            ln = rd = 0;
            ld = mn = md = rn = 1;

            //Set fraction we are searching for
            String[] fraction = line.split(" ");
            sn = Integer.parseInt(fraction[0]);
            sd = Integer.parseInt(fraction[1]);

            int comp;
            while((comp = compare(mn, md, sn, sd)) != 0){
                if(comp < 0){
                    int tempN = mn;
                    int tempD = md;

                    mn += rn;
                    md += rd;

                    ln = tempN;
                    ld = tempD;

                    out.append("R");

                } else {
                    int tempN = mn;
                    int tempD = md;

                    mn += ln;
                    md += ld;

                    rn = tempN;
                    rd = tempD;

                    out.append("L");
                }
            }
            out.append("\n");
        }
        out.close();
    }
}
