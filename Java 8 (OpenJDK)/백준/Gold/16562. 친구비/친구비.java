
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] parents;
	static int[] friendCosts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		friendCosts = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			friendCosts[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
		}
		int resultCost = 0;
		for (int i = 1; i <= N; i++) {
			if (parents[i] == i) {
				resultCost += friendCosts[i];
			}
		}
		if (resultCost > K) {
			System.out.println("Oh no");
		} else {
			System.out.println(resultCost);
		}
	}

	public static boolean union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb) {
			if (friendCosts[fa] < friendCosts[fb]) {

				parents[fb] = fa;
			} else {
				parents[fa] = fb;
			}
			return true;
		} else {
			return false;
		}
	}

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}
}
