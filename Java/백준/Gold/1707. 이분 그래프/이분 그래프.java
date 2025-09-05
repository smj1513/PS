import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int v, e;
	static ArrayList<Integer>[] graph;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[v + 1];
			for(int i = 1; i <= v; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			visited = new int[v + 1];
			boolean isPos = true;
			
			for(int i = 1; i <= v && isPos; i++) {
				if(visited[i] == 0) {
					if(!bfs(i)) {
						isPos = false;
					}
				}
			}
			
			sb.append(isPos ? "YES" : "NO").append("\n");
		}
		
		System.out.println(sb);

	}
	
	public static boolean bfs(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		visited[start] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : graph[cur]) {
				if(visited[next] == visited[cur]) {
					return false;
				}
				
				if(visited[next] == 0) {
					visited[next] = -visited[cur];
					q.offer(next);
				}
			}
		}
		
		return true;
	}

}
