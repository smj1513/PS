
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = new int[100][100];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int sum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			fill(x, y);

		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int drx = dx[k] + i;
						int dry = dy[k] + j;
						if (!valid(drx, dry) || map[drx][dry] == 0) {
							sum++;
						}
					}
				}
			}
		}
		System.out.println(sum);
	}

	static boolean valid(int x, int y) {
		return 0 <= x && x < 100 && 0 <= y && y < 100;
	}

	static void fill(int x, int y) {
		for (int i = y, iEnd = y + 10; i < iEnd; i++) {
			for (int j = x, jEnd = x + 10; j < jEnd; j++) {
				map[i][j] = 1;
			}
		}
	}
}
