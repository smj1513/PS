import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] meterial = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(meterial);
        int cnt = 0;
        for (int i = 0, j = N - 1; i < j; ) {
            int sum = meterial[i] + meterial[j];
            if (M == sum) {
                cnt++;
                i++;
                j--;
            } else if (M < sum) {
                j--;
            } else {
                i++;
            }
        }
        System.out.println(cnt);
    }
}
