
import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.Stream;

//백준 배열돌리기 1
public class Main {
    private static int N, M, R;
    private static int[][] matrix;
    private static int min;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        min = Math.min(N, M); //그룹을 나누기 위함

        matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < R; i++) {
            rotate();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void rotate() {
        int boundary = min / 2;
        for (int i = 0; i < boundary; i++) {
            int x = i;
            int y = i;
            int temp = matrix[x][y];
            for (int idx = 0; idx < 4; ) {
                int drx = dx[idx] + x;
                int dry = dy[idx] + y;
                if (i <= drx && drx < N - i && i <= dry && dry < M - i) {
                    matrix[x][y] = matrix[drx][dry];
                    x = drx;
                    y = dry;
                }else{
                    idx++;
                }
            }
            matrix[i+1][i] = temp;
        }
    }
}
