
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    static int[] matrix1;
    static int[] matrix2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        matrix1 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        matrix2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(matrix1);
        for (int a : matrix2) {
            int result = binarySearch(0, N-1, a);
            if (result == -1) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }

    }

    public static int binarySearch(int start, int end, int target) {
        int mid = (start + end) / 2;

        if (start > end) {
            return -1;
        }
        if (matrix1[mid] == target) {
            return mid;
        } else if (matrix1[mid] < target) {
            return binarySearch(mid+1, end, target);
        } else {
            return binarySearch(start, mid-1, target);
        }
    }
}
