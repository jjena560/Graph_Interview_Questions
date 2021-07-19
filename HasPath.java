import java.util.*;
import java.io.*;

// Edge is already defined
// class Edge {
//     int src, nbr, wt;

//     Edge(int src, int nbr, int wt) {
//         this.src = src;
//         this.nbr = nbr;
//         this.wt = wt;
//     }
// }

public class HasPath {

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest) {
            return true;
        }

        visited[src] = true;

        // we'll assume if the neighbor of the source has the path to the destination
        // then the source has the path to destination
        for (Edge edge : graph[src]) {
            if (visited[edge.nbr] == false) {
                // in an edge object we have -> src, nbr and then the weight and we need the
                // neighbor
                boolean hasNbrPath = hasPath(graph, edge.nbr, dest, visited);
                if (hasNbrPath == true) {
                    return true;
                }
            }

        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vtces = scn.nextInt();
        ArrayList<Edge>[] graph = new ArrayList[vtces];

        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }

        int edges = scn.nextInt();
        for (int i = 0; i < edges; i++) {
            String[] parts = scn.next().split(",");
            int v1 = Integer.parseInt(parts[0]); // vertices 1
            int v2 = Integer.parseInt(parts[1]); // vertices 2
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }

        int src = scn.nextInt();
        int dest = scn.nextInt(); // source and destination

        boolean[] visited = new boolean[vtces];

        System.out.println(hasPath(graph, src, dest, visited));
    }
}
