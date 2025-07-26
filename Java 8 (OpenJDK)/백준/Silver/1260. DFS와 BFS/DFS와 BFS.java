
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static List<LinkedList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int N, M, V;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nmv = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nmv[0];
		M = nmv[1];
		V = nmv[2];
		graph.add(null);
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			graph.add(new LinkedList<>());
		}
		for (int i = 0; i < M; i++) {
			int[] uv = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			graph.get(uv[0]).add(uv[1]);
			graph.get(uv[1]).add(uv[0]);
		}
		for (int i = 1 ; i < graph.size(); i++){
			Collections.sort(graph.get(i));
		}

		System.out.print(V + " ");
		dfs(V);
		System.out.println();

		visited = new boolean[N+1];

		System.out.print(V + " ");
		bfs(V);
	}

	public static void dfs(int v) {
		visited[v] = true;
		for(int next : graph.get(v)){
			if(!visited[next]){
				System.out.print(next + " ");
				dfs(next);
			}
		}
	}

	public static void bfs(int v){
		Queue<Integer> queue = new LinkedList<>();
		visited[v] = true;
		queue.add(v);
		while(!queue.isEmpty()){
			Integer current = queue.poll();
			for(int next : graph.get(current)){
				if(!visited[next]){
					visited[next] = true;
					queue.add(next);
					System.out.print(next + " ");
				}
			}
		}
	}
}
