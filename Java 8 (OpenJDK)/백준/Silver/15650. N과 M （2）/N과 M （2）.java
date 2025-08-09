
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] origin;
	static int[] numberTemp;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		origin = new int[N];
		numberTemp = new int[M];
		for (int i = 1; i <= N; i++) {
			origin[i - 1] = i;
		}
		combination(0, 0);

	}

	public static void combination(int start, int depth) {
		if (depth == M) {
			for (int i = 0; i < numberTemp.length; i++) {
				System.out.printf("%d ", numberTemp[i]);
			}
			System.out.println();
		} else {
			for (int i = start; i < N; i++) {
				numberTemp[depth] = origin[i];
				// 다음 원소는 현재 뽑은 원소의 다음 인덱스부터 뽑도록 재귀 호출(i+1)
				combination(i + 1, depth + 1);
			}
		}
	}
}