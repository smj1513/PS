
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int V, E;
	private static List<Integer>[] graph;
	private static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		inDegree = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			inDegree[to]++;
		}
		List<int[]> result = topologySort();
		for (int[] re : result){
			System.out.print(re[1]+" ");
		}
	}

	public static List<int[]> topologySort() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 1; i <= V; i++) {
			if (inDegree[i] == 0) {
				queue.add(new int[]{i, 1});
			}
		}
		int cnt = 0;
		List<int[]> cntList = new ArrayList<>();
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			cntList.add(current);
			for (int nv : graph[current[0]]) {
				if (--inDegree[nv] == 0) {
					queue.add(new int[]{nv, current[1] + 1});
				}
			}
		}
		cntList.sort(Comparator.comparingInt(a -> a[0]));
		return cntList;
	}
}
