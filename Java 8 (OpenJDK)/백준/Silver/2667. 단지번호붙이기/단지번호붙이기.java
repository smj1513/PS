
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static boolean[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) == '1';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j]) {
                    result.add(dfs(i, j));
                }
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for (Integer a : result) {
            System.out.println(a);
        }


    }

    public static int dfs(int x, int y) {
        if(!map[x][y]){
            return 0;
        }
        map[x][y] = false;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int drx = x + dx[i];
            int dry = y + dy[i];
            if (isValid(drx, dry) && map[drx][dry]) {
                sum += dfs(drx, dry);
            }
        }
        return sum + 1;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
