
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] distance = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) distance[i][j] = 0;
                    else distance[i][j] = 500_000_000;
                }
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                distance[from][to] = cost;
                distance[to][from] = cost;
            }
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
            int K = Integer.parseInt(br.readLine());
            int[] participant = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int idx = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                int sum = 0;
                for (int part : participant) {
                    sum += distance[i][part];
                }
                if (sum < min) {
                    min = sum;
                    idx = i;
                }
            }
            System.out.println(idx);
        }

    }
}
