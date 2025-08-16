
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int[][] matrix;
    static int cnt1, cnt2, cnt3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        divide(0,0 ,N);
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);

    }

    static void divide(int x, int y, int N) {
        int zero = 0;
        int one = 0;
        int minus = 0;
        for (int i = x, xEnd = x + N; i < xEnd; i++) {
            for (int j = y, yEnd = y + N; j < yEnd; j++) {
                if (matrix[i][j] == 0) {
                    ++zero;
                } else if (matrix[i][j] == 1) {
                    ++one;
                } else {
                    ++minus;
                }
            }
        }
        if (minus == N * N) {
            ++cnt1;
        } else if (zero == N * N) {
            ++cnt2;
        } else if (one == N * N) {
            ++cnt3;
        } else {
            int nextN = N / 3;
            divide(x, y, nextN);
            divide(x + nextN, y, nextN);
            divide(x + nextN + nextN, y, nextN);
            divide(x, y + nextN, nextN);
            divide(x, y + nextN + nextN, nextN);
            divide(x + nextN, y + nextN, nextN);
            divide(x + nextN, y + nextN + nextN, nextN);
            divide(x + nextN + nextN, y + nextN, nextN);
            divide(x + nextN + nextN, y + nextN + nextN, nextN);
        }
    }
}
