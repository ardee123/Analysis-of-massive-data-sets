import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;
public class SimHash {
    public static void main(String[] args) {
        int N, Q;
        ArrayList<String> text_hash = new ArrayList<>();
        //ArrayList<String> queries = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        System.out.println("NUmber of texts: "+N);

        for(int i = 0 ; i<N ; i++){
            String str = sc.nextLine();

            // izracunaj simhash od svake linije i spremi u listu
        }

        Q = sc.nextInt();
        System.out.println("Number of queries: "+Q);

        for(int i = 0; i<Q; i++){
            int line_id = sc.nextInt();
            int k = sc.nextInt();
            // zovi fju koja kalkulira hammingovu udaljenost
        }
    }

    public String calculateSimHash(String text){
        String [] words = text.split(" ");
        for(String word : words){
            byte [] hash = DigestUtils
        }
    }
}
