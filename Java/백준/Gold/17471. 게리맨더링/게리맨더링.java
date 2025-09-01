import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] population;
	static ArrayList<Integer>[] nearby;
	static int result = Integer.MAX_VALUE;
	static boolean[] selectedA;
	

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		population = new int[n + 1];
		for(int i = 1; i < n + 1; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		nearby = new ArrayList[n + 1];
		for(int i = 1; i < n + 1; i++) {
			nearby[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				nearby[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		selectedA = new boolean[n + 1];
		dfs(1);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);

	}
	
	public static void dfs(int idx) {
		if(idx == n + 1) {
			if(bfs(true) && bfs(false)) {
				int popA = getPop(true);
				int popB = getPop(false);
				result = Math.min(result, Math.abs(popA - popB));
			}
			
			return;
		}
		
		selectedA[idx] = true;
		dfs(idx + 1);
		
		selectedA[idx] = false;
		dfs(idx + 1);
	}
	
	public static boolean bfs(boolean isA) {
		boolean[] visited = new boolean[n + 1];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		int start = -1;
		for(int i = 1; i < n + 1; i++) {
			if(selectedA[i] == isA) {
				start = i;
				break;
			}
		}
		
		if(start == -1) {
			return false;
		}
		
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int next: nearby[cur]) {
				if(visited[next]) {
					continue;
				}
				
				if(selectedA[next] != isA) {
					continue;
				}
				
				visited[next] = true;
				q.offer(next);
			}
		}
		
		for(int i = 1; i < n + 1; i++) {
			if(selectedA[i] == isA && !visited[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int getPop(boolean isA) {
		int sum = 0;
		for(int i = 1; i < n + 1; i++) {
			if(selectedA[i] == isA) {
				sum += population[i];
			}
		}
		
		return sum;
	}

}
