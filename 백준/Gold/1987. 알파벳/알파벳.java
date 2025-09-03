
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static int R, C;
	//static boolean[] history = new boolean[26]; // set 보다는 알파벳 배열을 사용하도록하자..
	//HashSet을 사용하여 히스토리 기록시 300904KB 1992ms
	//boolean 배열 사용시 15416kb	928ms
	//static BitSet history = new BitSet(26);
	//BitSet은 대상 개수가 적을때는 boolean 배열에 비해 느림.
	//BitSet 사용시 17140kb 1268ms

	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		//비트마스킹 사용
		int history = 1 << (map[0][0] - 'A');
		dfs(0, 0, 1, history);
		System.out.println(cnt);


	}

	public static void dfs(int x, int y, int depth, int history) {
		cnt = Math.max(cnt, depth);

		for (int i = 0; i < 4; i++) {
			int drx = x + dx[i];
			int dry = y + dy[i];
			if (isValid(drx, dry)) {
				int nextIdx = map[drx][dry] - 'A';
				if ((history & (1 << nextIdx)) == 0) {
					int newHistory = history | (1 << nextIdx);
					dfs(drx, dry, depth + 1, newHistory);
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return 0 <= x && x < R && 0 <= y && y < C;
	}
}