
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] inDegree;
    static int V, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        inDegree = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            graph.add(new LinkedList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            ++inDegree[to]; //진입차수 증가
        }
        String s = topologySort();
        System.out.println(s);
    }

    public static String topologySort() {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                stringBuilder.append(i).append(" ");
            }
        }
        //큐 사이즈가 0이면 위상정렬 불가
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (--inDegree[next] == 0) {
                    stringBuilder.append(next).append(" ");
                    queue.add(next);
                }
            }
        }
        return stringBuilder.toString();
    }
}
