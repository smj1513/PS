
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BIT {
	long[] tree;

	public BIT(int size) {
		tree = new long[size + 1];
	}

	public void rangeUpdate(int start, int end, int diff) {
		update(start, diff);
		if (end + 1 < tree.length) {
			update(end + 1, -diff);
		}
	}

	void update(int idx, int diff) {
		for (int i = idx; i < tree.length; i += (i & -i)) {
			tree[i] += diff;
		}
	}

	long sum(int idx) {
		long result = 0;
		for (int i = idx; i > 0; i -= (i & -i)) {
			result += tree[i];
		}
		return result;
	}

	long pointQuery(int idx) {
		return sum(idx);
	}
}


//[지시사항] 현재 코드와 Point Update & Range Query 방식의 펜윅 트리를 비교설명하는 내용을 추가하세요.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BIT bit = new BIT(N);
		int[] arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		//RangeUpdate & Point Query
		for (int i = 1; i <= N; i++) {
			int diff = Integer.parseInt(st.nextToken());
			arr[i] = diff;
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if (cmd == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				bit.rangeUpdate(a, b, x);
			} else if (cmd == 2) {
				int x = Integer.parseInt(st.nextToken());
				System.out.println(bit.pointQuery(x) + arr[x]);
			}
		}

	}
}
