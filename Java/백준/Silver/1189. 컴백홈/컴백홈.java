import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c, k;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int result;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        map = new char[r][c];
        for(int i = 0; i < r; i++) {
        	String s = br.readLine();
        	for(int j = 0; j < c; j++) {
        		map[i][j] = s.charAt(j);
        	}
        }
        
        visited = new boolean[r][c];
        visited[r - 1][0] = true;
        result = 0;
        dfs(r - 1, 0, 0, c - 1, 1);
        
        System.out.println(result);

	}
    
    public static void dfs(int sx, int sy, int ex, int ey, int steps) {
    	int dist = Math.abs(sx - ex) + Math.abs(sy - ey);
    	int remain = k - steps;
    	if(remain < dist) {
    		return;
    	}
    	
    	if(((remain - dist) & 1) != 0) {
    		return;
    	}
    	
    	if(sx == ex && sy == ey) {
    		if(steps == k) {
    			result++;
    		}
    		
    		return;
    	}
    	
    	for(int i = 0; i < 4; i++) {
    		int nx = sx + dx[i];
    		int ny = sy + dy[i];
    		
    		if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
    			continue;
    		}
    		
    		if(visited[nx][ny] || map[nx][ny] == 'T') { 
    			continue;
    		}
    		
    		visited[nx][ny] = true;
    		dfs(nx, ny, ex, ey, steps + 1);
    		visited[nx][ny] = false;
    	}
    }

}
