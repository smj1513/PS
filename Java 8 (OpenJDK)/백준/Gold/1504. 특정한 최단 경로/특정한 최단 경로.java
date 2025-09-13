
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
	int v, cost;

	public Vertex(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static int N, E;
	static int[] distance;
	static int INF = 500_000_000;
	static List<Vertex>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		distance = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Vertex(to, cost));
			graph[to].add(new Vertex(from, cost));
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int toA = minDistance(1, a);
		int toB = minDistance(1, b);
		int AtoB = minDistance(a, b);

		int AtoEnd = minDistance(a, N);
		int BtoEnd = minDistance(b, N);
		int re = Math.min(toA + AtoB + BtoEnd, toB + AtoB + AtoEnd);

		System.out.println(re >= INF ? -1 : re);

	}

	public static int minDistance(int start, int end) {
		if (start == end) {
			return 0;
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(start, 0));
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
			if (cur.v == end) {
				return distance[cur.v];
			}
			if (visited[cur.v] || distance[cur.v] < cur.cost) {
				continue;
			}
			visited[cur.v] = true;
			for (Vertex nv : graph[cur.v]) {
				if (!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.cost) {
					distance[nv.v] = distance[cur.v] + nv.cost;
					pq.offer(new Vertex(nv.v, distance[nv.v]));
				}
			}
		}
		return distance[end];
	}

}
