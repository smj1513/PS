/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	
	static int n;
	static int[][] cells;
	static ArrayList<int[]> cores;
	static int bestConnected, bestLength;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			n = Integer.parseInt(br.readLine());
			
			cells = new int[n][n];
			cores = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 1; i < n - 1; i++) {
				for(int j = 1; j < n - 1; j++) {
					if(cells[i][j] == 1) {
						cores.add(new int[] { i, j });
					}
				}
			}
			
			bestConnected = 0;
			bestLength = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append(bestLength).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	public static void dfs(int idx, int connected, int length) {
		int remain = cores.size() - idx;
		if(connected + remain < bestConnected) {
			return;
		}
		
		if(idx == cores.size()) {
			if(connected > bestConnected) {
				bestConnected = connected;
				bestLength = length;
			} else if(connected == bestConnected && length < bestLength) {
				bestLength = length;
			}
			
			return;
		}
		
		int x = cores.get(idx)[0];
		int y = cores.get(idx)[1];
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int len = 0;
			
			ArrayList<int[]> path = new ArrayList<>();
			
			while(0 <= nx && nx < n && 0 <= ny && ny < n) {
				if(cells[nx][ny] != 0) {
					len = 0;
					break;
				}
				
				path.add(new int[] { nx, ny });
				nx += dx[i];
				ny += dy[i];
				len++;
			}
			
			if(len > 0) {
				for(int[] p : path) {
					cells[p[0]][p[1]] = 2;
				}
				dfs(idx + 1, connected + 1, length + len);
				for(int[] p : path) {
					cells[p[0]][p[1]] = 0;
				}
			}
		}
		
		dfs(idx + 1, connected, length);
	}

}
