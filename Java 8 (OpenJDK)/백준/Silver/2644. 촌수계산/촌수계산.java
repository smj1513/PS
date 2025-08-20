
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        int bfs = bfs(x, y);
        System.out.println(bfs);

    }

    public static int bfs(int start, int end) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int next : graph.get(current[0])) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new int[]{next, current[1] + 1});
                    if (next == end) {
                        return current[1] + 1;
                    }
                }
            }
        }
        return -1;
    }
}
