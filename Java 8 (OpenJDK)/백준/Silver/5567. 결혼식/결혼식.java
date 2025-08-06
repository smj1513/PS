
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
	static List<List<Integer>> graph = new ArrayList<>(500);
	static int cnt = 0;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			graph.add(new LinkedList<>());
		}
		for (int i = 0; i < m; i++) {
			int[] ab = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(ab[0]).add(ab[1]);
			graph.get(ab[1]).add(ab[0]);
		}
		bfs(1);
		System.out.println(cnt);
	}

	static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(new int[]{start, 0});
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int v : graph.get(cur[0])) {
				if (!visited[v] && cur[1] < 2) {
					cnt++;
					visited[v] = true;
					queue.add(new int[]{v, cur[1] + 1});
				}
			}
		}
	}

}
