
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Integer[] roop = new Integer[N];

		for (int i = 0; i < N; i++) {
			roop[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(roop, Collections.reverseOrder());
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, roop[i] * (i + 1));
		}
		System.out.println(max);
	}
}
