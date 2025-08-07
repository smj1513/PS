
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String[][] matrix;
    static int n;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        matrix = new String[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().split("");
        }


        for (int i = 0; i < n; i++) { //일반인 구역 탐색
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.print(cnt + " ");

        for (int i = 0; i < n; i++) { //적록 색약 버전으로 맵 변경
            for (int j = 0; j < n; j++) {
                if (matrix[i][j].equals("G")) {
                    matrix[i][j] = "R";
                }
            }
        }

        visited = new boolean[n][n];
        cnt = 0;
        for (int i = 0; i < n; i++) { //적록 색약 탐색
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.print(cnt++);

    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (valid(drx, dry) && !visited[drx][dry] && matrix[x][y].equals(matrix[drx][dry])) {
                dfs(drx, dry);
            }
        }
    }

    public static boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
