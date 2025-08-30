
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int[][] map;
	static int white, blue;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		divide(0, 0, N);
		System.out.println(white);
		System.out.println(blue);

	}

	public static void divide(int x, int y, int size) {
		int sum = 0;
		for (int i = x, iEnd = x + size; i < iEnd; i++) {
			for (int j = y, jEnd = y + size; j < jEnd; j++) {
				sum += map[i][j];
			}
		}

		if (sum == 0) {
			white++;
			return;
		} else if (sum == size * size) {
			blue++;
			return;
		} else {
			int newSize = size / 2;
			divide(x, y, newSize);
			divide(x, y+newSize, newSize);
			divide(x+newSize, y, newSize);
			divide(x+newSize, y+newSize, newSize);
		}

	}
}
