
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] temp = new int[N];
		int lol = 1;
		temp[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > temp[lol - 1]) {
				temp[lol++] = arr[i];
				continue;
			}
			int pos = Arrays.binarySearch(temp, 0, lol, arr[i]);
			if (pos < 0) {
				pos = -pos - 1;
			}
			temp[pos] = arr[i];
			if (pos == lol) {
				lol++;
			}
		}
		System.out.println(lol);
	}
}
