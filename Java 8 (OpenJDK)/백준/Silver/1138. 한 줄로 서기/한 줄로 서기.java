
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] line = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] result = new int[N];
		for (int i = 0, j = N - 1; j >= 0; i++, j--) {
			int cnt = line[j];
			if (cnt == i) {
				result[i] = j + 1;
			} else {
				for (int k = i; k > cnt; k--) {
					result[k] = result[k - 1];
				}
				result[cnt] = j + 1;

			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i <N;i++){
			sb.append(result[i]).append(' ');
		}
		System.out.println(sb);
	}
}
