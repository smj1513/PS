
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
	static int N, M;
	static List<Vertex>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Vertex(B, C));
			graph[B].add(new Vertex(A, C));
		}
		System.out.println(minDistance());
	}

	public static int minDistance() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		int[] distance = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		Arrays.fill(distance, 1_000_000_000);
		distance[1] = 0;
		pq.add(new Vertex(1, 0));
		while (!pq.isEmpty()) {
			Vertex v = pq.poll();
			if (v.v == N) {
				break;
			}
			if(visited[v.v]){
				continue;
			}
			visited[v.v] = true;
			for (Vertex nv : graph[v.v]) {
				if (distance[nv.v] >= distance[v.v] + nv.w) {
					distance[nv.v] = distance[v.v] + nv.w;
					pq.offer(new Vertex(nv.v, distance[nv.v]));
				}
			}
		}
		return distance[N];
	}
}
