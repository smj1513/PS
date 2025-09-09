
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int lisLength = 0;
            int[] LIS = new int[N];
            for (int i = 0; i < N; i++) {
                int pos = Arrays.binarySearch(LIS, 0, lisLength, arr[i]);
                if (pos < 0) {
                    //삽입 위치를 찾았을 경우
                    pos = -(pos + 1);
                }
                LIS[pos] = arr[i];
                if (pos == lisLength) {
                    lisLength++;
                }
            }
            System.out.println("#" + tc + " " + lisLength);
        }
    }
}
