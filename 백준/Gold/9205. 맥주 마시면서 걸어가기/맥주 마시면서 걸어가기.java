
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Vertex {
	int v;
	int cost;


	public Vertex(int v, int cost) {
		this.v = v;
		this.cost = cost;
	}

}

public class Main {
	static int N;
	static List<Vertex>[] graph;

	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			graph = new List[N + 2];
			visited = new boolean[N + 2];
			Point[] points = new Point[N + 2];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				graph[i] = new ArrayList<>();
				points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < N + 2; i++) {//완전그래프 생성
				for (int j = i + 1; j < N + 2; j++) {
					int cost = distance(points[i], points[j]);
					graph[i].add(new Vertex(j, cost));
					graph[j].add(new Vertex(i, cost));
				}
			}
			if (bfs()) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	public static boolean bfs() {
		Queue<Vertex> q = new ArrayDeque<>();
		q.offer(new Vertex(0, 0));
		visited[0] = true;
		while (!q.isEmpty()) {
			Vertex cur = q.poll();
			for (Vertex nv : graph[cur.v]) {
				if (!visited[nv.v] && nv.cost <= 1000) {
					q.offer(new Vertex(nv.v, nv.cost));
					visited[nv.v] = true;
				}
			}
		}
		return visited[N + 1];
	}

	public static int distance(Point v1, Point v2) {
		return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
	}
}
