
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		int truthKnowCnt = Integer.parseInt(st.nextToken());
		if (truthKnowCnt == 0) {
			System.out.println(M);
			return;
		}

		// 진실을 아는 사람을 저장
		ArrayList<Integer> truthKnowers = new ArrayList<>();
		for (int i = 0; i < truthKnowCnt; i++) {
			truthKnowers.add(Integer.parseInt(st.nextToken()));
		}

		// 파티 정보를 저장할 리스트
		ArrayList<Integer>[] parties = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int partySize = Integer.parseInt(st.nextToken());

			// 파티의 첫 번째 사람을 기준으로 나머지 사람들을 union
			int firstPerson = Integer.parseInt(st.nextToken());
			parties[i].add(firstPerson);

			for (int j = 1; j < partySize; j++) {
				int nextPerson = Integer.parseInt(st.nextToken());
				parties[i].add(nextPerson);
				union(firstPerson, nextPerson);
			}
		}

		int possibleParties = 0;
		for (int i = 0; i < M; i++) {
			boolean canLie = true;
			if (parties[i].isEmpty()) continue; // 빈 파티는 스킵

			int partyRoot = find(parties[i].get(0));

			for (int knower : truthKnowers) {
				if (partyRoot == find(knower)) {
					canLie = false;
					break;
				}
			}
			if (canLie) {
				possibleParties++;
			}
		}
		System.out.println(possibleParties);
	}

	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb) {
			if (fa < fb) {
				parents[fb] = fa;
			} else {
				parents[fa] = fb;
			}
		}
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
}