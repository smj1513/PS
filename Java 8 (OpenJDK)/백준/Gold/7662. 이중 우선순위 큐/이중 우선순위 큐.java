import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				String command = st.nextToken();
				int value = Integer.parseInt(st.nextToken());
				switch (command) {
					case "I":
						// 중복되는 값의 개수를 저장
						treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);
						break;
					case "D":
						if (!treeMap.isEmpty()) {
							if (value == -1) { // 최솟값 삭제
								int firstKey = treeMap.firstKey();
								int count = treeMap.get(firstKey);
								if (count == 1) {
									treeMap.remove(firstKey);
								} else {
									treeMap.put(firstKey, count - 1);
								}
							} else { // 최댓값 삭제
								int lastKey = treeMap.lastKey();
								int count = treeMap.get(lastKey);
								if (count == 1) {
									treeMap.remove(lastKey);
								} else {
									treeMap.put(lastKey, count - 1);
								}
							}
						}
						break;
				}
			}
			if (treeMap.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.printf("%d %d\n", treeMap.lastKey(), treeMap.firstKey());
			}
		}
	}
}
