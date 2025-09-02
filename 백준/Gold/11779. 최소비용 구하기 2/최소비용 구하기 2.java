
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    int v, w;

    public Vertex(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.w - o.w;
    }
}

public class Main {
    static List<Vertex>[] graph;
    static int V, E;
    static int[] distance;
    static boolean[] visited;
    static int start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        graph = new List[V + 1];
        visited = new boolean[V + 1];
        distance = new int[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[from].add(new Vertex(to, w));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        int[] history = dijkstra(start, end);
        System.out.println(distance[end]);
        int idx;
        Stack<Integer> stack = new Stack<>();

        for (idx = end; idx != start; idx = history[idx]) {
            stack.push(idx);
        }
        stack.push(start);
        System.out.println(stack.size());
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }

    public static int[] dijkstra(int start, int end) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        distance[start] = 0;
        int[] parents = new int[V + 1];
        pq.add(new Vertex(start, 0));
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            if (distance[cur.v] < cur.w) {
                continue;
            }
            if (visited[cur.v]) {
                continue;
            }
            visited[cur.v] = true;
            if (cur.v == end) {
                return parents;
            }
            for (Vertex nv : graph[cur.v]) {
                if (!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.w) {
                    distance[nv.v] = distance[cur.v] + nv.w;
                    pq.offer(new Vertex(nv.v, distance[nv.v]));
                    parents[nv.v] = cur.v;
                }
            }
        }
        return parents;
    }
}
