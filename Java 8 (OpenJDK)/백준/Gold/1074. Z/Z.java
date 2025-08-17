
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, r, c, cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		int size = (int) Math.pow(2, N);
		findOrder(0, 0, size);

		System.out.println(cnt);
	}

	public static void findOrder(int x, int y, int size) {
		if (size == 1) {
			return;
		}

		int newSize = size / 2;
		int area = newSize * newSize;

		// 1사분면
		if (r < x + newSize && c < y + newSize) {
			findOrder(x, y, newSize);
		}
		// 2사분면
		else if (r < x + newSize && c >= y + newSize) {
			cnt += area;
			findOrder(x, y + newSize, newSize);
		}
		// 3사분면
		else if (r >= x + newSize && c < y + newSize) {
			cnt += area * 2;
			findOrder(x + newSize, y, newSize);
		}
		// 4사분면
		else {
			cnt += area * 3;
			findOrder(x + newSize, y + newSize, newSize);
		}
	}
}