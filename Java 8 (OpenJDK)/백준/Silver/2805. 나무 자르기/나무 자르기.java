
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] tree;
	//찾아야할 최적 해 = 높이
	//시작점, 끝점 = 가장 작은 나무 높이, 가장 큰 나무 높이
	static int start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //파라미터 개수
		long M = Long.parseLong(st.nextToken()); //찾아야할 최적 해
		tree = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(tree[i], end);
		}
		long result = parametricSearch(start, end, M);
		System.out.println(result);
	}

	public static long parametricSearch(long start, long end, long target) {
		while (start < end) {
			long mid = start + (end - start) / 2; //톱 높이
			long total = 0;
			for (int tr : tree) {
				total += (mid > tr ? 0 : (tr - mid)); //톱날 높이보다 나무가 작으면 안잘리니까 0
			}
			if (total < target){ //목표치 보다 적게 잘랐을 경우 톱 높이를 낮춘다. // lower bound
				end = mid;
			}else{
				start = mid + 1; //
			}
		}
		return end - 1;
	}
}