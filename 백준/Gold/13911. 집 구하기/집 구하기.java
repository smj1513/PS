
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

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
    static int V, E;
    static int M, x, S, y;
    static List<Vertex>[] graph;
    static int macBoundary, starBoundary;
    static int[] macdoList;
    static Set<Integer> macSet = new HashSet<>();
    static Set<Integer> starSet = new HashSet<>();
    static int[] starList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Vertex(v, w));
            graph[v].add(new Vertex(u, w));
        }
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        macdoList = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        starList = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] macMin = minDistance(macdoList, macSet);
        int[] starMin = minDistance(starList, starSet);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            int macDis = macMin[i];
            int starDis = starMin[i];
            if (macDis <= x && starDis <= y && !macSet.contains(i) && !starSet.contains(i)) {
                min = Math.min(min, macDis + starDis);
            }
        }
        System.out.println(min == Integer.MAX_VALUE? "-1" : min);

    }

    public static int[] minDistance(int[] start, Set<Integer> temp) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        int[] distance = new int[V + 1];
        Arrays.fill(distance, 1_000_000_000);
        for (int s : start) {
            temp.add(s);
            pq.offer(new Vertex(s, 0));
            distance[s] = 0;
        }
        boolean[] visited = new boolean[V + 1];
        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            if (visited[cur.v] && cur.w > distance[cur.v]) {
                continue;
            }
            visited[cur.v] = true;
            for (Vertex nv : graph[cur.v]) {
                if (!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.w) {
                    distance[nv.v] = distance[cur.v] + nv.w;
                    pq.offer(new Vertex(nv.v, distance[nv.v]));
                }
            }
        }
        return distance;
    }
}
