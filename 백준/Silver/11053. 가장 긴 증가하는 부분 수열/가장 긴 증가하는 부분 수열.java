
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
        int[] lis = new int[N];
        int lengthOfLis = 1;
        lis[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (lis[lengthOfLis - 1] < arr[i]) {
                lis[lengthOfLis++] = arr[i];
                continue;
            }
            int pos = Arrays.binarySearch(lis, 0, lengthOfLis, arr[i]);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            lis[pos] = arr[i];
            if (pos == lengthOfLis) {
                lengthOfLis++;
            }
        }
        System.out.println(lengthOfLis);
    }
}
