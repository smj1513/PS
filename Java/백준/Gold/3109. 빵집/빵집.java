
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1}; //오른쪽 위, 오른쪽, 오른쪽 아래,
    static int[] dy = {1, 1, 1};
    static String[][] matrix;
    static boolean[][] visited;
    static int R, C;
    static int cnt = 0;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        matrix = new String[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < C; j++) {
                String token = line[j];
                matrix[i][j] = token;
            }
        }
        for (int i = 0; i < R; i++) {
            dfs(i, 0);
            flag = false;
        }
        System.out.println(cnt);
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 3; i++) {
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if (valid(drx, dry) && !visited[drx][dry]
                    && matrix[drx][dry].equals(".")) {
                if (dry == C - 1) {
                    cnt++;
                    flag = true;
                }
                dfs(drx,dry);
                if(flag){
                    break;
                }
            }
        }
    }

    static boolean valid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
