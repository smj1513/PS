
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int R, C;
    static char[][] map;
    static boolean[] alpha;
    static int max = 0;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            max = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            alpha = new boolean[26];
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
            }
            dfs(0, 0, 1);
            System.out.println("#"+tc+" "+max);
        }
    }

    public static void dfs(int x, int y, int depth) {
        alpha[map[x][y] - 'A'] = true;
        max = Math.max(max, depth);
        if(depth == 26){
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isValid(nx, ny) && !alpha[map[nx][ny] - 'A']){
                dfs(nx, ny, depth + 1);
                alpha[map[nx][ny] - 'A'] = false;
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
