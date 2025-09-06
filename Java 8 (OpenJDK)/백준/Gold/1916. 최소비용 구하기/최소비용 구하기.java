
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
	int v, w;

	public Vertex(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.w - o.w;
	}
}

public class Main {
	static List<Vertex>[] graph;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Vertex(to, cost));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		System.out.println(bfs(start, end));

	}

	public static int bfs(int start, int end) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0));
		int[] distance = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if (cur.v == end) {
				return distance[cur.v];
			}
			if (visited[cur.v] || distance[cur.v] < cur.w) {
				continue;
			}
			visited[cur.v] = true;
			for (Vertex nv : graph[cur.v]) {
				if (!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.w) {
					distance[nv.v] = distance[cur.v] + nv.w;
					pq.offer(new Vertex(nv.v, distance[nv.v]));
				}
			}
		}
		return distance[end];
	}
}
