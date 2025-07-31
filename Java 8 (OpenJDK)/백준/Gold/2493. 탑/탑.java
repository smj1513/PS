
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Stack<int[]> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			if (stack.isEmpty()) {
				System.out.print("0 ");
				stack.push(new int[]{i + 1, arr[i]});
			} else {
				while (true) {
					if (stack.isEmpty()) {
						System.out.print("0 ");
						stack.push(new int[]{i + 1, arr[i]});
						break;
					}

					if (stack.peek()[1] > arr[i]) {
						System.out.print(stack.peek()[0] + " ");
						stack.push(new int[]{i + 1, arr[i]});
						break;
					} else {
						stack.pop();
					}
				}
			}

		}


	}
}
