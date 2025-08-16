
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static int[][] matrix;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;

    static int N, L, R;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        matrix = new int[N][];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int answer = 0;
        while (true) {
            boolean check = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int change = bfs(i, j);
                        if (change >= 2) {
                            check = true;
                        }
                    }
                }
            }
            if (!check) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    public static int bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        Queue<int[]> queue1 = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        queue1.add(new int[]{x, y});
        visited[x][y] = true;

        int cnt = 1;
        int sum = matrix[x][y];
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curX = current[0];
            int curY = current[1];
            for (int i = 0; i < 4; i++) {
                int drx = dx[i] + curX;
                int dry = dy[i] + curY;
                if (valid(drx, dry) && !visited[drx][dry]) {
                    int diff = Math.abs(matrix[curX][curY] - matrix[drx][dry]);
                    if (L <= diff && diff <= R) {
                        sum += matrix[drx][dry];
                        cnt++;
                        visited[drx][dry] = true;
                        queue.add(new int[]{drx, dry});
                        queue1.add(new int[]{drx, dry});
                    }
                }
            }
        }
        if (cnt == 1) {
            return 0;
        } else {
            while (!queue1.isEmpty()) {
                int[] point = queue1.poll();
                matrix[point[0]][point[1]] = sum / cnt;
            }
            return cnt;
        }
    }


    public static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

}
