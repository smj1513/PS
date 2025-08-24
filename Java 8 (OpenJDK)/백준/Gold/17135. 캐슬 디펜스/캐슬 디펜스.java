
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
	static int[][] map;
	static int N, M, D;
	static int maxKill;
	static int[] range;
	static int[] archer = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		range = new int[M];
		for (int i = 0; i < M; i++) {
			range[i] = i;
		}
		for(int i = 0 ; i < N;i++){
			map[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		combination(0, 0);
		System.out.println(maxKill);
	}

	static void combination(int start, int depth) {
		if (depth == 3) {
			play();
		} else {
			for (int i = start; i < M; i++) {
				archer[depth] = range[i];
				combination(i + 1, depth + 1);
			}
		}
	}

	static void play() {
		int[][] clone = new int[N][M];
		for (int i = 0; i < N; i++) {
			clone[i] = map[i].clone();
		}
		int kill = 0;
		//턴 진행
		for (int turn = 0; turn < N; turn++) {
			Set<int[]> targets = new HashSet<>();
			//현재 조합에서 궁수들이 공격할 적 찾기
			for (int pos : archer) {
				// 사거리 내에서 공격할 적들을 정렬시키며 저장
				PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
					int dist1 = Math.abs(N - a[0]) + Math.abs(pos - a[1]);
					int dist2 = Math.abs(N - b[0]) + Math.abs(pos - b[1]);
					if (dist1 == dist2) {
						return a[1] - b[1];
					} else {
						return dist1 - dist2;
					}
				});
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (clone[r][c] == 1) {
							int distance = Math.abs(N - r) + Math.abs(pos - c);
							if (distance <= D) {
								pq.add(new int[]{r, c});
							}
						}
					}
				}
				//공격할 대상이 있다면
				if (!pq.isEmpty()) {
					targets.add(pq.poll());
				}
			}
			//대상들을 공격
			for (int[] target : targets) {
				if (clone[target[0]][target[1]] == 1) {
					clone[target[0]][target[1]] = 0;
					kill++;
				}
			}
			//적이 한칸씩 아래로 이동
			for (int i = N - 1; i > 0; i--) {
				clone[i] = clone[i - 1].clone();
			}
			Arrays.fill(clone[0], 0);
			maxKill = Math.max(maxKill, kill);
		}
	}
}