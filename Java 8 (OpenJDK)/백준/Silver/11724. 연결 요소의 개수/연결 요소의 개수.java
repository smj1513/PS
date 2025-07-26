import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
	static List<LinkedList<Integer>> graph;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = nm[0], M = nm[1];
		visited = new boolean[N + 1];
		graph = new ArrayList<>(N + 1);
		graph.add(new LinkedList<>());
		for (int i = 1; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		for (int i = 0; i < M; i++) {
			int[] uv = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(uv[0]).addFirst(uv[1]);
			graph.get(uv[1]).addFirst(uv[0]);
		}


		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	public static void dfs(int node) {
		visited[node] = true;
		for (Integer i : graph.get(node)) {
			if (!visited[i]) {
				dfs(i);
			}

		}

	}
}