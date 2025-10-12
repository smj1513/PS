
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
	static int[] distance;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		graph = new List[D + 1];
		distance = new int[D + 1];
		visited = new boolean[D + 1];
		Arrays.fill(distance, 100_000_000);

		for (int i = 0; i <= D; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < D; i++) {
			graph[i].add(new Vertex(i + 1, 1));
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if(to<=D){
				graph[from].add(new Vertex(to, cost));
			}
		}

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, 0));
		distance[0] = 0;
		while (!pq.isEmpty()) {
			Vertex cur = pq.poll();
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
		System.out.println(distance[D]);
	}
}
