import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c;
	static char[][] map;
	static int[][] fireDist, jihunDist;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static ArrayDeque<int[]> fireQ = new ArrayDeque<>();
	static ArrayDeque<int[]> jihunQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        map = new char[r][c];
        fireDist = new int[r][c];
        jihunDist = new int[r][c];
        
        for(int i = 0; i < r; i++) {
        	Arrays.fill(fireDist[i], -1);
        	Arrays.fill(jihunDist[i], -1);
        }
        
        for(int i = 0; i < r; i++) {
        	String s = br.readLine();
        	for(int j = 0; j < c; j++) {
        		char ch = s.charAt(j);
        		map[i][j] = ch;
        		if(ch == 'F') {
        			fireQ.offer(new int[] { i, j });
        			fireDist[i][j] = 0;
        		}
        		
        		if(ch == 'J') {
        			jihunQ.offer(new int[] { i, j });
        			jihunDist[i][j] = 0;
        		}
        	}
        }
        
        bfsFire();
        bfsJihun();

	}
    
    public static void bfsFire() {
    	while(!fireQ.isEmpty()) {
    		int[] cur = fireQ.poll();
    		int curX = cur[0];
    		int curY = cur[1];
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = curX + dx[i];
    			int ny = curY + dy[i];
    			
    			if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
    				continue;
    			}
    			
    			if(map[nx][ny] == '#' || fireDist[nx][ny] != -1) {
    				continue;
    			}
    			
    			fireDist[nx][ny] = fireDist[curX][curY] + 1;
    			fireQ.offer(new int[] { nx, ny });
    		}
    	}
    }
    
    public static void bfsJihun() {
    	while(!jihunQ.isEmpty()) {
    		int[] cur = jihunQ.poll();
    		int curX = cur[0];
    		int curY = cur[1];
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = curX + dx[i];
    			int ny = curY + dy[i];
    			
    			if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
    				System.out.println(jihunDist[curX][curY] + 1);
    				return;
    			}
    			
    			if(map[nx][ny] == '#' || jihunDist[nx][ny] != -1) {
    				continue;
    			}
    			
    			if(fireDist[nx][ny] != -1 && fireDist[nx][ny] <= jihunDist[curX][curY] + 1) {
    				continue;
    			}
    			
    			jihunDist[nx][ny] = jihunDist[curX][curY] + 1;
    			jihunQ.offer(new int[] { nx, ny });
    		}
    	}
    	
    	System.out.println("IMPOSSIBLE");
    }

}
