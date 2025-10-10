import java.io.*;
import java.util.*;

public class Main {
	static List<Integer>[] graph;
	static int[] parent;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		graph = new List[N+1];
		visited = new boolean[N+1];
		for (int i = 0 ; i <= N ;i++){
			graph[i] = new LinkedList<>();
		}
		parent = new int[N+1];
		for (int i = 0 ; i < N-1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[to].add(from);
			graph[from].add(to);
		}
		dfs(1);
		for (int i = 2;  i <=N;i++){
			int a = parent[i];
			sb.append(a).append("\n");
		}
		System.out.println(sb);
	}
	public static void dfs(int v){
		visited[v] = true;
		for (Integer nv : graph[v]){
			if(!visited[nv]){
				dfs(nv);
				parent[nv] = v;
			}
		}
	}
}