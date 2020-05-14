import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PCY {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //parser inputa
        int brKosara = Integer.parseInt(sc.nextLine());
        float s = Float.parseFloat(sc.nextLine());
        int brPretinaca = Integer.parseInt(sc.nextLine());
        int prag = (int) Math.floor(s * brKosara);

        //ucitaj predmete iz kosare
        List<Kosara> kosare = new ArrayList<>();
        for (int i = 0; i < brKosara; i++) {
            List<Integer> predmeti = new ArrayList<>();
            String[] predmeti_string = sc.nextLine().split(" ");
            for (int j = 0; j < predmeti_string.length; j++)
                predmeti.add(Integer.parseInt(predmeti_string[j]));
            Kosara k = new Kosara(predmeti);
            kosare.add(k);
        }

        // A-priori algoritam
        Map<Integer, Integer> brPredmeta = new HashMap<>();
        for (Kosara k : kosare) {
            for (Integer item : k.predmeti) {
                if (brPredmeta.containsKey(item)) {
                    brPredmeta.put(item, brPredmeta.get(item) + 1);
                } else {
                    brPredmeta.put(item, 1);
                }
            }
        }
        int m = predmeti_u_prvom_prolazu(brPredmeta, prag);
        System.out.println(m); // output A

        // PCY dio algoritma
        int[] pretinci = new int[brPretinaca];
        for (Kosara k : kosare) {
            List<Integer> predmeti_arr = k.predmeti;
            for (int i = 0; i < predmeti_arr.size(); i++) {
                for (int j = i + 1; j < predmeti_arr.size(); j++) {
                    int p1 = predmeti_arr.get(i);
                    int p2 = predmeti_arr.get(j);
                    if (brPredmeta.get(p1) >= prag && brPredmeta.get(p2) >= prag) {
                        int k2 = ((p1 * brPredmeta.size()) + p2) % brPretinaca;
                        pretinci[k2]++;
                    }
                }
            }
        }

        Map<String, Integer> parovi = new HashMap<>();
        for (Kosara k : kosare) {
            List<Integer> predmeti_arr = k.predmeti;
            for (int i = 0; i < predmeti_arr.size(); i++) {
                for (int j = i + 1; j < predmeti_arr.size(); j++) {
                    int p1 = predmeti_arr.get(i);
                    int p2 = predmeti_arr.get(j);
                    String p = p1 + "," + p2;
                    if (brPredmeta.get(p1) >= prag && brPredmeta.get(p2) >= prag) {
                        int k2 = ((p1 * brPredmeta.size()) + p2) % brPretinaca;
                        if (pretinci[k2] >= prag) {
                            if (parovi.containsKey(p)) {
                                int brPonavljanja = parovi.get(p);
                                parovi.put(p, brPonavljanja + 1);
                            } else {
                                parovi.put(p, 1);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(parovi.size()); // output P
        zavrsna_lista_ispis(parovi); // output X

    }

    public static void zavrsna_lista_ispis(Map<String, Integer> parovi) {
        Collection<Integer> parovi_ponavljanje = parovi.values();
        List<Integer> lista = new ArrayList<>();
        for (Integer i : parovi_ponavljanje) {
            lista.add(i);
        }
        Collections.sort(lista, Collections.reverseOrder());
        for (Integer i : lista)
            System.out.println(i); // output
    }

    public static int predmeti_u_prvom_prolazu(Map<Integer, Integer> brPredmeta, int prag) {
        int m = 0;
        for (Map.Entry<Integer, Integer> entry : brPredmeta.entrySet()) {
            if (entry.getValue() >= prag) {
                m++;
            }
        }

        return (m * (m - 1) / 2);
    }

    public static class Kosara {
        List<Integer> predmeti;

        public Kosara(List<Integer> predmeti) {
            this.predmeti = predmeti;
        }
    }
}
