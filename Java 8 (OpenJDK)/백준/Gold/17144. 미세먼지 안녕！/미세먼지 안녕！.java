
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	static int[][] airCleanerPos = new int[2][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		int cleanerIdx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airCleanerPos[cleanerIdx++] = new int[]{i, j};
				}
			}
		}

		// T초 동안 시뮬레이션 실행
		for (int t = 0; t < T; t++) {
			spread();
			airCleaning();
		}

		// 남은 미세먼지 양 계산
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					sum += map[i][j];
				}
			}
		}
		System.out.println(sum);
	}

	// 1. 미세먼지 확산
	public static void spread() {
		// 확산 결과를 저장할 임시 배열
		int[][] tempMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) { // 미세먼지가 있는 칸
					int spreadAmount = map[i][j] / 5;
					int spreadCount = 0;

					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];

						// 배열 범위 안이고, 공기청정기가 아닌 곳으로 확산
						if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1) {
							tempMap[nr][nc] += spreadAmount;
							spreadCount++;
						}
					}
					// 남은 미세먼지 양
					map[i][j] -= spreadAmount * spreadCount;
				}
			}
		}

		// 확산된 양과 남은 양을 합쳐서 업데이트
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += tempMap[i][j];
			}
		}
	}

	// 2. 공기청정기 작동
	public static void airCleaning() {
		int topRow = airCleanerPos[0][0];
		int bottomRow = airCleanerPos[1][0];

		// 아래 -> 위
		for (int i = topRow - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 왼쪽 -> 오른쪽
		for (int j = 0; j < C - 1; j++) {
			map[0][j] = map[0][j + 1];
		}
		// 위 -> 아래
		for (int i = 0; i < topRow; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// 오른쪽 -> 왼쪽
		for (int j = C - 1; j > 1; j--) {
			map[topRow][j] = map[topRow][j - 1];
		}
		map[topRow][1] = 0; // 공기청정기에서 나온 바람

		// 위 -> 아래
		for (int i = bottomRow + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// 왼쪽 -> 오른쪽
		for (int j = 0; j < C - 1; j++) {
			map[R - 1][j] = map[R - 1][j + 1];
		}
		// 아래 -> 위
		for (int i = R - 1; i > bottomRow; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// 오른쪽 -> 왼쪽
		for (int j = C - 1; j > 1; j--) {
			map[bottomRow][j] = map[bottomRow][j - 1];
		}
		map[bottomRow][1] = 0; // 공기청정기에서 나온 바람
	}
}