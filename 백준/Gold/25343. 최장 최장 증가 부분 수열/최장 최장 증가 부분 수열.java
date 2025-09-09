
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, 0}; //오른쪽 아래
    static int[] dy = {0, 1};
    static int N;
    static int[][] map;
    static int[][] dp;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < i + 1; k++) {
                    for (int l = 0; l < j + 1; l++) {
                        if (map[k][l] < map[i][j]) {
                            dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
                        }
                    }
                }
                max = Math.max(dp[i][j], max);
            }
        }
        System.out.println(max + 1);
    }
//
//    public static void dfs(int x, int y, List<Integer> currentPath) {
//        currentPath.add(map[x][y]);
//        visited[x][y] = true;
//        if (x == N - 1 && y == N - 1) {
//            max = Math.max(max, lis(currentPath));
//        }
//        for (int i = 0; i < 2; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//
//            if (isValid(nx, ny) && !visited[nx][ny]) {
//                dfs(nx, ny, currentPath);
//            }
//        }
//        currentPath.remove(currentPath.size() - 1);
//
//        visited[x][y] = false;
//    }

}
