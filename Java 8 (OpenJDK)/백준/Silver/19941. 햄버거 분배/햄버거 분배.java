
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		char[] list = br.readLine().toCharArray();
		int eater = 0;
		int mid = list.length / 2;
		for (int i = 0; i < list.length; i++) {
			char ch = list[i];
			if (ch == 'H') {
				for (int j = i - K; j <= i + K; j++) {
					if (j >= 0 && j < list.length) {
						char c = list[j];
						if (c == 'P') {
							list[i] = 'E';
							list[j] = 'E';
							eater++;
							break;
						}
					}
				}
			}
		}
		System.out.println(eater);
	}
}
