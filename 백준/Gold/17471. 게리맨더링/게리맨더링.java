
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] population, area;
    static List<Integer>[] graph;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        population = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            population[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        area = new int[N + 1];
        area[1] = 1;
        dfs(2);
        if (min == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }

    public static void dfs(int k) {
        if (k == N + 1) {
            int area1 = 0;
            int area2 = 0;
            for (int i = 1; i <= N; i++) {
                if (area[i] == 1) area1 += population[i];
                else area2 += population[i];
            }
            visited = new boolean[N + 1];
            int link = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    bfs(i, area[i]);
                    link++;
                }
            }
            if (link == 2) min = Math.min(min, Math.abs(area1 - area2));
            return;
        }

        area[k] = 1;
        dfs(k + 1);

        area[k] = 2;
        dfs(k + 1);
    }

    public static void bfs(int idx, int areanum) {
        Queue<Integer> q = new LinkedList<>();
        visited[idx] = true;
        q.offer(idx);

        // bfs 메서드 내부
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : graph[current]) {
                if (area[next] == areanum && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
}
