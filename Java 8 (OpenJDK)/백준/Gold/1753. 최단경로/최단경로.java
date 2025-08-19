
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int vertax;
    int weight;

    public Edge(int vertax, int weight) {
        this.vertax = vertax;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}


public class Main {
    static List<LinkedList<Edge>> graph = new ArrayList<>();
    static int[] distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new LinkedList<>());
        }
        distance = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).addFirst(new Edge(v, w));
        }
        dijkstra(start);
        for (int i = 1; i <= V; i++) {
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF" : distance[i]);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (!visited[current.vertax]) {
                for (Edge next : graph.get(current.vertax)) {
                    if (distance[next.vertax] > distance[current.vertax] + next.weight) {
                        distance[next.vertax] = distance[current.vertax] + next.weight;
                        pq.add(new Edge(next.vertax, distance[next.vertax]));
                    }
                }
            }
        }
    }
}
