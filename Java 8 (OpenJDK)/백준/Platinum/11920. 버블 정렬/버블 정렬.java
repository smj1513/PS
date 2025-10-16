import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < N;i++){
			pq.offer(array[i]);
			if(i >= K){
				sb.append(pq.poll()).append(" ");
			}
		}
		while (!pq.isEmpty()){
			sb.append(pq.poll()).append(" ");
		}
		System.out.println(sb);
	}
}