
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex implements Comparable<Vertex> {
	int v;
	double x, y;
	double cost;

	public Vertex(int v, double x, double y) {
		this.v = v;
		this.x = x;
		this.y = y;
	}

	public Vertex(int v, double x, double y, double cost) {
		this.v = v;
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	double calcDistance(Vertex other) {
		double x = this.x - other.x;
		double y = this.y - other.y;
		return Math.sqrt((x * x) + (y * y));
	}

	@Override
	public int compareTo(Vertex o) {
		return Double.compare(this.cost, o.cost);
	}
}

public class Main {
	static List<Vertex>[] graph;
	static double[] inEdge;
	static boolean[] visited;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new List[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		Vertex[] vertexs = new Vertex[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			vertexs[i] = new Vertex(i, x, y);
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				graph[i].add(vertexs[j]);
				graph[j].add(vertexs[i]);
			}
		}
		double result = prim(vertexs[0]);
		System.out.printf("%.2f", result);
	}

	public static double prim(Vertex start) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.add(new Vertex(start.v, start.x, start.y, 0));
		inEdge = new double[N];
		Arrays.fill(inEdge, Double.MAX_VALUE);
		inEdge[start.v] = 0;
		double result = 0.0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Vertex current = pq.poll();
			if (visited[current.v]) {
				continue;
			}
			visited[current.v] = true;
			result += current.cost;
			if(++cnt == N){
				break;
			}
			for (Vertex nv : graph[current.v]) {
				if (!visited[nv.v] && inEdge[nv.v] > nv.cost) {
					double distance = current.calcDistance(nv);
					inEdge[nv.v] = distance;
					pq.offer(new Vertex(nv.v, nv.x, nv.y, distance));
				}
			}
		}
		return result;
	}
}
