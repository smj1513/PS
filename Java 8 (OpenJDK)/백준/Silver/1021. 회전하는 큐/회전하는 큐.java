
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
	static LinkedList<Integer> deque;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Stream.of(br
				.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int N = nm[0];
		int M = nm[1];

		deque = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			deque.offer(i);
		}
		int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int n : array) {
			int targetIdx = deque.indexOf(n);
			int mid = deque.size() / 2;
			if (targetIdx <= mid) {
				while (deque.peekFirst() != n) {
					rotateLeft();

				}
			} else {
				while (deque.peekFirst() != n) {
					rotateRight();
				}
			}
			deque.removeFirst();
		}
		System.out.println(cnt);
	}

	public static void rotateLeft() {
		deque.offerLast(deque.pollFirst());
		cnt++;
	}

	public static void rotateRight() {
		deque.offerFirst(deque.pollLast());
		cnt++;
	}
}