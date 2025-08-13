
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Egg {
	final int weight;
	int durability;

	Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;

	}

	void crash(Egg other) {
		this.durability -= other.weight;
		other.durability -= this.weight;
	}

	boolean isBroken() {
		return this.durability <= 0;
	}

	void refresh(Egg other) {
		this.durability += other.weight;
		other.durability += this.weight;

	}
}


public class Main {
	static int N;
	static Egg[] eggs;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(d, w);
		}
		backTracking(0);
		System.out.println(max);

	}

	public static void backTracking(int depth) {
		if (depth == N) {
			//종료시점에 깨진 계란의 수를 계산
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				if (eggs[i].isBroken()) {
					cnt++;
				}
			}
			max = Math.max(max, cnt);
			return;
		}
		//현재 든 손의 계란이 깨졌다면
		if (eggs[depth].isBroken()) {
			backTracking(depth + 1);
			return;
		} else {
			boolean allOtherEggsBroken = true;
			//다른 모든 계란(i)을 순회하며 쳐봄
			for (int i = 0; i < eggs.length; i++) {
				//자기 자신은 칠 수없음
				if (depth == i) continue;
				//이미 깨진 계란은 칠 수 없음
				if (eggs[i].isBroken()) continue;
				//칠 계란이 있음
				allOtherEggsBroken = false;
				//계란 타격
				eggs[depth].crash(eggs[i]);
				//다음 계란으로 넘어가서 재귀 호출
				backTracking(depth + 1);
				//상태 복원
				eggs[depth].refresh(eggs[i]);
			}
			// 손에 든 계란은 깨지지 않았으면서, 다른 계란이 모두 깨져있다면,
			// 다음 계란으로 이동
			if (allOtherEggsBroken) {
				backTracking(depth + 1);
			}
		}
	}
}
