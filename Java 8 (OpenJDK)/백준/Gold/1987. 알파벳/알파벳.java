
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static int R, C;
    static Set<Character> history = new HashSet<>();
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        dfs(0, 0, 1);
        System.out.println(cnt);


    }

    public static void dfs(int x, int y, int depth) {
        history.add(map[x][y]);
        cnt = Math.max(cnt, depth);
        for (int i = 0; i < 4; i++) {
            int drx = x + dx[i];
            int dry = y + dy[i];
            if (isValid(drx, dry) && !history.contains(map[drx][dry])) {
                dfs(drx, dry, depth + 1);
                history.remove(map[drx][dry]);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
