
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

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
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            int[] inputs = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1, jNext = j + 1; j < inputs[0]; j++, jNext++) {
                int from = inputs[j];
                int to = inputs[jNext];
                graph.get(from).add(to);
                inDegree[to]++;
            }
        }
        List<Integer> integers = topologySort();
        if (integers.isEmpty() || integers.size() != V) {
            System.out.println(0);
        } else {
            integers.forEach(System.out::println);
        }
    }

    public static List<Integer> topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                result.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (int next : graph.get(current)) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                    result.add(next);
                }
            }
        }
        return result;
    }
}
