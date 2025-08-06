import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}
		List<Integer> result = bfs(X, K);
		Collections.sort(result);
		if (result.isEmpty()) {
			System.out.println(-1);
		} else {
			for (int a : result) {
				System.out.println(a);
			}
		}
	}

	static List<Integer> bfs(int n, int k) {
		List<Integer> result = new ArrayList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { n, 0 });
		visited[n] = true;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (cur[1] == k) {
				result.add(cur[0]);
			} else {
				for (int v : graph.get(cur[0])) {
					if (!visited[v]) {
						visited[v] = true;
						queue.add(new int[] { v, cur[1] + 1 });
					}
				}
			}
		}
		return result;
	}

}