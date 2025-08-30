
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
	int to;
	int cost;

	public Vertex(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Vertex o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static List<Vertex>[] graph;
	static int V, E;
	static boolean[] visited;
	static int[] inEdge;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new List[V + 1];
		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new boolean[V + 1];
		inEdge = new int[V + 1];
		Arrays.fill(inEdge, Integer.MAX_VALUE);
		int start = 0, minCost = Integer.MAX_VALUE;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Vertex(to, cost));
			graph[to].add(new Vertex(from, cost));
			if (minCost > cost) {
				start = from;
			}
		}
		int mstCost = prim(1);
		System.out.println(mstCost);
	}

	public static int prim(int start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(start, 0));
		inEdge[start] = 0;
		int result = 0;
		int cnt = 0;
		int maxCost = 0;
		while (!pq.isEmpty()) {
			Vertex current = pq.poll();
			if (visited[current.to]) {
				continue;
			}
			visited[current.to] = true;
			result += current.cost;
			maxCost = Math.max(current.cost, maxCost);
			if (++cnt == V) {
				result -= maxCost;
				break;
			}
			for (Vertex nv : graph[current.to]) {
				if (!visited[nv.to] && inEdge[nv.to] > nv.cost) {
					pq.offer(new Vertex(nv.to, nv.cost));
					inEdge[nv.to] = nv.cost;
				}
			}
		}
		return result;
	}
}
