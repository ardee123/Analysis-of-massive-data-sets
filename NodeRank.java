import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NodeRank {
    public static int NUM_OF_NODES;
    public static int MAX_ITER = 100;
    public static int Q;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String [] line = sc.nextLine().split(" ");
        NUM_OF_NODES = Integer.valueOf(line[0]);
        double beta_value = Double.parseDouble(line[1]);
        List<Node> list_of_nodes = new ArrayList<>();

        for(int i = 0; i<NUM_OF_NODES; i++){
            Node node = new Node(i);
            list_of_nodes.add(node);
        }

        for(int i = 0; i<NUM_OF_NODES; i++){
            line = sc.nextLine().split(" ");
            for(int j = 0; j< line.length; j++){
                int coming_value = Integer.parseInt(line[j]);
                list_of_nodes.get(coming_value).coming.add(i);
                list_of_nodes.get(i).rank = line.length;
            }
        }

        double [][] ranks = calculate_node_rank(list_of_nodes, beta_value);
        Q = Integer.parseInt(sc.nextLine());

        for(int i = 0; i<Q; i++){
            line = sc.nextLine().split(" ");
            get_rank(Integer.parseInt(line[0]), Integer.parseInt(line[1]), ranks);
        }
    }

    private static void get_rank(int node, int iter, double[][] ranks) {
        NumberFormat formatter = new DecimalFormat("#0.0000000000");
        System.out.println(formatter.format(ranks[iter][node]));
    }

    private static double [][] calculate_node_rank(List<Node> list_of_nodes, double beta_value) {
        double [][] ranks = new double[MAX_ITER+1][NUM_OF_NODES];
        for(int i = 0; i< NUM_OF_NODES; i++){
            ranks[0][i] =  (double) 1/NUM_OF_NODES;
        }

        for(int i = 1 ; i<MAX_ITER+1; i++){
            double S = 0;
            for(int j = 0; j<NUM_OF_NODES; j++){
                Node node = list_of_nodes.get(j); // uzmi nulti cvor
                for(Integer coming_node : node.coming){
                    ranks[i][j] += (beta_value*ranks[i-1][coming_node]) / list_of_nodes.get(coming_node).rank;
                }
                S += ranks[i][j];
            }

            for(int j =0; j<NUM_OF_NODES; j++){
                ranks[i][j] += (1 - S) / NUM_OF_NODES;
            }
        }

        return ranks;
    }

    private static class Node{
        int node_id;
        int rank = 0;
        List<Integer> coming = new ArrayList<>();

        public Node(int node_id) {
            this.node_id = node_id;
        }

        public int getNode_id() {
            return node_id;
        }

        public List<Integer> getComing() {
            return coming;
        }
    }
}
