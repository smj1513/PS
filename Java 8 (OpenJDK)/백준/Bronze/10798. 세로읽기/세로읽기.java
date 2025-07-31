import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] matrix = new String[5][];
        for (int i = 0; i < 5; i++) {
            matrix[i] = br.readLine().split("");
        }
        int max = matrix[0].length;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < 5; j++) {
                max = Math.max(max, matrix[j].length);
                if (i < matrix[j].length) {
                    System.out.print(matrix[j][i]);
                }
            }
        }

    }
}
