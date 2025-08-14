
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(press(0, 0, N));
    }

    static String press(int startX, int startY, int size) {
        int sum = 0;
        for (int i = startX, xEnd = startX + size; i < xEnd; i++) {
            for (int j = startY, yEnd = startY + size; j < yEnd; j++) {
                sum += matrix[i][j];
            }
        }
        if (sum == 0) {
            return "0";
        } else if (sum == size * size) {
            return "1";
        } else {
            int newSize = size / 2;
            String one = press(startX, startY, newSize);
            String two = press(startX, startY + newSize, newSize);
            String three = press(startX + newSize, startY, newSize);
            String four = press(startX + newSize, startY + newSize, newSize);
            return "(" + one + two + three + four + ")";
        }
    }
}
