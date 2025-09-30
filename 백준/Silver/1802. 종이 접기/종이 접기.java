
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int[] inputs = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            if (inputs.length > 1 && inputs.length % 2 == 0) {
                System.out.println("NO");
                continue;
            }

            if (divide(inputs, 0, inputs.length - 1)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean divide(int[] input, int start, int end) {
        if (start == end) {
            return true;
        }
        int mid = (start + end) / 2;
        for (int i = 0; i < mid - start; i++) {
            if (input[start + i] == input[end - i]) {
                return false;
            }
        }

        return divide(input, start, mid - 1) && divide(input, mid + 1, end);
    }
}
