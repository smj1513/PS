import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		int[] array = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(Arrays.stream(array).min().getAsInt() + " " + Arrays.stream(array).max().getAsInt());
	}
}
