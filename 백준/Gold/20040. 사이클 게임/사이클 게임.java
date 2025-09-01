import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		int i;
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (!union(a, b)) {
				break;
			}
		}
		br.close();
		System.out.println(i == m ? 0 : i + 1);
	}

	static boolean union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb) {
			parents[fb] = fa;
			return true;
		}
		return false;
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		} else {
			return parents[a] = find(parents[a]);
		}
	}

}
