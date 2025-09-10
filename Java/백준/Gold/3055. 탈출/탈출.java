import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int x;
		int y;
		int time;
		
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int r, c;
	static char[][] map;
	static boolean[][] visited;
	static ArrayDeque<int[]> waterQ;
	static ArrayDeque<Point> hogQ;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		waterQ = new ArrayDeque<>();
		hogQ = new ArrayDeque<>();
		
		map = new char[r][c];
		visited = new boolean[r][c];
		int sx = -1;
		int sy = -1;
		for(int i = 0; i < r; i++) {
			String str = br.readLine();
			for(int j = 0; j < c; j++) {
				char ch = str.charAt(j);
				map[i][j] = ch;
				if(ch == 'S') {
					sx = i;
					sy = j;
					visited[i][j] = true;
					hogQ.offer(new Point(i, j, 0));
					map[i][j] = '.';
				} else if(ch == '*') {
					waterQ.offer(new int[] { i, j });
				}
			}
		}
		int result = bfs();
		System.out.println(result == -1 ? "KAKTUS" : result);

	}
	
	public static int bfs() {
		while(!hogQ.isEmpty()) {
			int wSize = waterQ.size();
			for(int i = 0; i < wSize; i++) {
				int[] cur = waterQ.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				for(int j = 0; j < 4; j++) {
					int nx = curX + dx[j];
					int ny = curY + dy[j];
					
					if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
						continue;
					}
					
					if(map[nx][ny] == '.') {
						map[nx][ny] = '*';
						waterQ.offer(new int[] { nx, ny });
					}
				}
			}
			
			int hSize = hogQ.size();
			for(int i = 0; i < hSize; i++) {
				Point cur = hogQ.poll();
				int curX = cur.x;
				int curY = cur.y;
				int curT = cur.time;
				
				for(int j = 0; j < 4; j++) {
					int nx = curX + dx[j];
					int ny = curY + dy[j];
					
					if(nx < 0 || ny < 0 || nx >= r || ny >= c) {
						continue;
					}
					
					if(map[nx][ny] == 'D') {
						return curT + 1;
					}
					
					if(map[nx][ny] == '.' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						hogQ.offer(new Point(nx, ny, curT + 1));
					}
				}
			}
		}
		
		return -1;
	}

}
