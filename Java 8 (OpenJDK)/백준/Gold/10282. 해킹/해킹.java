
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
	static int N, D, start;
	static List<Vertex>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			graph = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[to].add(new Vertex(from, cost));
			}
			int[] result = dijkstra(start);
			System.out.println(result[0] + " " + result[1]);
		}
	}

	public static int[] dijkstra(int start){
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(start, 0));
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, 1_000_000_000);
		distance[start] = 0;
		int[] result = new int[2];
		while (!pq.isEmpty()){
			Vertex cur = pq.poll();
			if(visited[cur.v] || distance[cur.v] < cur.cost){
				continue;
			}
			result[0]++;
			result[1] = cur.cost;
			visited[cur.v] = true;
			for (Vertex nv : graph[cur.v]){
				if(!visited[nv.v] && distance[nv.v] > distance[cur.v] + nv.cost){
					distance[nv.v] = distance[cur.v] + nv.cost;
					pq.offer(new Vertex(nv.v, distance[nv.v]));
				}
			}
		}
		return result;
	}
}
