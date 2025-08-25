
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	// 8방 탐색을 위한 배열 (상, 하, 좌, 우, 대각선)
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	static char[][] map;
	static int[][] countMap; // 주변 지뢰 개수를 저장할 배열
	static int N;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			countMap = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			// 1. 전처리: 각 칸의 주변 지뢰 개수 계산
			preprocess();

			int clicks = 0;

			// 2. 0인 칸을 먼저 클릭하여 연쇄 반응 처리
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (countMap[i][j] == 0 && !visited[i][j]) {
						dfs(i, j);
						clicks++;
					}
				}
			}

			// 3. 연쇄 반응으로 열리지 않은 나머지 숫자 칸들 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 지뢰가 아니고, 아직 방문하지 않은 칸은 개별 클릭 대상
					if (countMap[i][j] > 0 && !visited[i][j]) {
						clicks++;
					}
				}
			}

			System.out.println("#" + tc + " " + clicks);
		}
	}

	// 각 칸의 주변 8방향 지뢰 개수를 미리 계산하는 함수
	public static void preprocess() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '*') {
					countMap[i][j] = -1; // 지뢰는 -1로 표시
					continue;
				}

				int mineCount = 0;
				for (int d = 0; d < 8; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isValid(nx, ny) && map[nx][ny] == '*') {
						mineCount++;
					}
				}
				countMap[i][j] = mineCount;
			}
		}
	}

	// 0인 칸을 중심으로 연쇄 반응을 처리하는 DFS
	public static void dfs(int x, int y) {
		visited[x][y] = true;

		// 현재 칸이 0일 경우에만 주변으로 확장
		if (countMap[x][y] == 0) {
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (isValid(nx, ny) && !visited[nx][ny] && countMap[nx][ny] != -1) {
					dfs(nx, ny);
				}
			}
		}
		// 현재 칸이 0이 아닌 숫자 칸이면, 방문 처리만 되고 더 이상 퍼져나가지 않음 (연쇄 반응 중지)
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}