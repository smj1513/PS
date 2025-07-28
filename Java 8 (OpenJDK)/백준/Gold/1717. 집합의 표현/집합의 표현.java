
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = nm[0];
		int m = nm[1];

		parent = new int[n+1];
		for(int i = 1; i <= n; i++){
			parent[i] = i;
		}
		for (int i = 0; i < m; i++){
			int[] qab = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int question = qab[0];
			int a = qab[1];
			int b = qab[2];
			if(question == 0){
				union(a,b);
			}else{
				System.out.println(checksum(a,b) ? "YES" : "NO");
			}
		}
	}

	public static void union(int a, int b) { // 연결 연산
		int fa = find(a);
		int fb = find(b);
		if (fa != fb){
			parent[fb] = fa; // 두 개 연결
		}
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a; //대표 노드일 경우 리턴
		} else {
			return parent[a] = find(parent[a]); // 대표노드 값을 parent 값으로 저장(좌표압축)
			//value를 index로 바꿔서 또 찾아보기
			//재귀를 빠져나올 때 마다 대표 노드를 업데이트 하라
		}
	}
	public static boolean checksum(int a, int b){ // a와 b가 같은 집합인가 확인
		return find(a) == find(b);
	}
}
