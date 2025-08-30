
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int from, to, cost;

	public Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static Edge[] graph; //그래프를 간선의 집합으로 표현
	static int[] parent;
	static int V, E;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new Edge[E];
		parent = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(graph);
		int result = 0;
		int maxCost = 0;
		for (Edge edge: graph){
			if(union(edge.from, edge.to)){
				result += edge.cost;
				maxCost = Math.max(maxCost, edge.cost);
			}
		}
		System.out.println(result-maxCost);
	}


	static boolean union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb) {
			parent[fb] = fa;
			return true;
		}
		return false;
	}

	static int find(int a) {
		if (parent[a] == a) {
			return a;
		} else {
			return parent[a] = find(parent[a]);
		}
	}
}
