import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static ArrayList<Integer>[] heights, reverse;
	static boolean[] visited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		heights = new ArrayList[n + 1];
		reverse = new ArrayList[n + 1];
		for(int i = 1; i < n + 1; i++) {
			heights[i] = new ArrayList<>();
			reverse[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			heights[a].add(b);
			reverse[b].add(a);
		}
		
		result = 0;
		for(int i = 1; i < n + 1; i++) {
			int bigger = bfs(i, heights);
			int smaller = bfs(i, reverse);
			
			if(bigger + smaller == n - 1) {
				result++;
			}
		}
		
		System.out.println(result);
		
	}
	
	public static int bfs(int start, ArrayList<Integer>[] list) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited = new boolean[n + 1];
		
		q.offer(start);
		visited[start] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next : list[cur]) {
				if(visited[next]) {
					continue;
				}
				
				visited[next] = true;
				q.offer(next);
				cnt++;
			}
		}
		
		return cnt;
	}

}
