
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] array = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			array[i + 1] = a == 1;
		}
		int sN = Integer.parseInt(br.readLine());
		for (int i = 0; i < sN; i++) {
			st = new StringTokenizer(br.readLine());
			int genderNum = Integer.parseInt(st.nextToken());
			int NUM = Integer.parseInt(st.nextToken());
			switch (genderNum) {
				case 1:
					for (int j = 1; j < array.length; j++) {
						if (j % NUM == 0) {
							array[j] = !array[j];
						}
					}
					break;
				case 2:
					for (int j = NUM, k = NUM; j > 0 && k < array.length && array[j] == array[k] ; j--, k++) {
						if (j == k) {
							array[j] = !array[j];
						} else {
							array[j] = !array[j];
							array[k] = !array[k];
						}
					}
					break;
			}
		}
		for (int i = 1; i < array.length; i++) {
			System.out.print((array[i] ? 1 : 0) + " ");
			if (i % 20 == 0) {
				System.out.println();
			}
		}
	}
}
