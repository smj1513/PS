
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		StringBuilder sb = new StringBuilder();
		starPrint(0, 0, N, false);
		for (char[] arr: map){
			for (char ch : arr){
				sb.append(ch);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void starPrint(int x, int y, int N, boolean isBlank) {
		if (isBlank) {
			for (int i = x, iEnd = x + N; i < iEnd; i++) {
				for (int j = y, jEnd = j + N; j < jEnd; j++) {
					map[i][j] = ' ';
				}
			}
			return;
		}
		if (N == 1) {
			map[x][y] = '*';
			return;
		}
		int newSize = N / 3;
		int cnt = 0;
		for (int i = x, iEnd = x + N; i < iEnd; i += newSize) {
			for (int j = y, jEnd = y + N; j < jEnd; j += newSize) {
				starPrint(i, j, newSize, ++cnt == 5);
			}
		}
	}
}
