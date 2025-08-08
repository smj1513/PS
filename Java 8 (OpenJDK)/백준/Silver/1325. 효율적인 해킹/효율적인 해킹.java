import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(b).add(a);
        }

        int[] hackCount = new int[N + 1];
        int maxCount = 0;

        for (int i = 1; i <= N; i++) {
            hackCount[i] = bfs(i); // 수정된 BFS 호출
            maxCount = Math.max(maxCount, hackCount[i]);
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (hackCount[i] == maxCount) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }


    private static int bfs(int startNode) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(startNode);
        visited[startNode] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int v : graph.get(current)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                    count++;
                }
            }
        }
        return count;
    }
}