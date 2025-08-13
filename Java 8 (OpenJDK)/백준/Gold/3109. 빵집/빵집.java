
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1}; //오른쪽 위, 오른쪽, 오른쪽 아래,
    static int[] dy = {1, 1, 1};
    static char[][] matrix;
    static boolean[][] visited;
    static int R, C;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        matrix = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                matrix[i][j] = line[j];
            }
        }
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }
        System.out.println(cnt);
    }

    public static boolean dfs(int x, int y) {
        visited[x][y] = true;
        if (y == C - 1) {
            cnt++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (valid(drx, dry) && !visited[drx][dry]
                    && matrix[drx][dry] == '.') {
                if(dfs(drx,dry)) return true;
            }
        }
        return false;
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
