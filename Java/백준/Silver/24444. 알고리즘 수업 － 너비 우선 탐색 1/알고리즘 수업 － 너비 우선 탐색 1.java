import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, r;
	static ArrayList<Integer>[] list;
	static int[] orderOf;
	static boolean[] visited;
	static int order = 0;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
        	list[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	list[a].add(b);
        	list[b].add(a);
        }
        
        for(int i = 1; i <= n; i++) {
        	Collections.sort(list[i]);
        }
        
        orderOf = new int[n + 1];
        visited = new boolean[n + 1];
        bfs(r);
        
        for(int i = 1; i <= n; i++) {
        	sb.append(orderOf[i]).append("\n");
        }
        
        System.out.println(sb);

	}
    
    public static void bfs(int r) {
    	ArrayDeque<Integer> q = new ArrayDeque<>();
    	
    	q.offer(r);
    	visited[r] = true;
    	orderOf[r] = ++order;
    	
    	while(!q.isEmpty()) {
    		int cur = q.poll();
    		
    		for(int next : list[cur]) {
    			if(visited[next]) {
    				continue;
    			}
    			
    			visited[next] = true;
    			orderOf[next] = ++order;
    			q.offer(next);
    		}
    	}
    }
    
}
