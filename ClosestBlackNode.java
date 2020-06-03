import java.util.*;

public class ClosestBlackNode {

    public static int N;
    public static int E;
    public static int NOT_AVAILABLE = -1;
    public static Queue<Node> queue;
    public static void main(String[] args) {

        queue = new LinkedList<Node>();

        Scanner sc = new Scanner(System.in);
        String [] line = sc.nextLine().split(" ");
        List<Node> nodes = new ArrayList<>();
        List<Node> black_nodes = new ArrayList<>();

        N = Integer.valueOf(line[0]);
        E = Integer.valueOf(line[1]);

        for(int i=0; i<N; i++){
            int val = Integer.valueOf(sc.nextLine());
            Node node = new Node(i);
            if(val == 0)
                node.isBlack = false;
            else {
                node.isBlack = true;
                node.closest_black = i;
                black_nodes.add(node);
            }
            nodes.add(node);
        }

        for(int i =0; i<E; i++){
            line = sc.nextLine().split(" ");
            int first_node = Integer.valueOf(line[0]);
            int second_node = Integer.valueOf(line[1]);
            nodes.get(first_node).neighbours.add(nodes.get(second_node));
            nodes.get(second_node).neighbours.add(nodes.get(first_node));
        }

        find_closest_black(black_nodes, nodes);
    }

    private static void find_closest_black(List<Node> black_nodes, List<Node> nodes) {
        for(Node n : black_nodes){
            BFS(n);
            reset_visited(nodes);
        }
        print_blacks(nodes);
    }

    private static void print_blacks(List<Node> nodes) {
        for(Node n : nodes){
            if(n.distance >10 || n.closest_black == NOT_AVAILABLE){
                System.out.println(NOT_AVAILABLE + " " + NOT_AVAILABLE);
            } else {
                System.out.println(n.closest_black + " " + n.distance);
            }
        }
    }

    private static void reset_visited(List<Node> nodes) {
        for(Node n : nodes){
            n.visited=false;
        }
    }

    public static void BFS(Node node)
    {
        queue.add(node);
        node.visited=true;
        while (!queue.isEmpty())
        {

            Node element=queue.remove();
            List<Node> neighbours=element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node n=neighbours.get(i);

                if(((n.distance ==0 && n.isBlack==false)|| n.distance > element.distance+1) && n.visited==false) {
                    n.distance = element.distance + 1;
                    if(element.isBlack == true){
                        n.closest_black = element.node_id;
                    } else {
                        n.closest_black = node.node_id;
                    }
                }

                if(n!=null && !n.visited)
                {
                    queue.add(n);
                    n.visited=true;

                }
            }

        }
    }


    static class Node
    {
        int node_id;
        int distance = 0;
        int closest_black = NOT_AVAILABLE;
        boolean isBlack = false;
        boolean visited;
        List<Node> neighbours;

        Node(int data)
        {
            this.node_id=data;
            this.neighbours=new ArrayList<>();

        }
        public void addneighbours(Node neighbourNode)
        {
            this.neighbours.add(neighbourNode);
        }
        public List getNeighbours() {
            return neighbours;
        }
        public void setNeighbours(List neighbours) {
            this.neighbours = neighbours;
        }

    }
}
