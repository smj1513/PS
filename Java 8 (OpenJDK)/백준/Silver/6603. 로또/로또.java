
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] S;
    static int[] temp;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = -1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if (K == 0) {
                break;
            }
            S = new int[K];
            temp = new int[6];
            for (int i = 0; i < K; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            combination(0, 0);
            System.out.println();

        }
    }

    public static void combination(int start, int depth) {
        if (depth == 6) {
            StringBuilder sb = new StringBuilder();
            for (int j : temp) {
                sb.append(j).append(' ');
            }
            System.out.println(sb);
        } else {
            for (int i = start; i < K; i++) {
                temp[depth] = S[i];
                combination(i + 1, depth + 1);
            }
        }
    }
}
