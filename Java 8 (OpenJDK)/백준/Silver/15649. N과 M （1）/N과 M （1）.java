
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static boolean[] visited;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M];
        visited = new boolean[N + 1];
        permutation(0);

    }

    public static void permutation(int cnt) {
        if (cnt == M) {
            for (int n : numbers) {
                System.out.print(n + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < N; i++) {
            if(!visited[i]){
                visited[i] = true;
                numbers[cnt] = i+1;
                permutation(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
