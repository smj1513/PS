
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
    int x, y, depth;
    boolean up;

    public Pair(int x, int y, int depth, boolean up) {
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.up = up;
    }
}

public class Solution {
    static int[][] map;
    static int x1, y1, x2, y2;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            map = new int[201][201];
            StringTokenizer st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken()) + 100;
            y1 = Integer.parseInt(st.nextToken()) + 100;
            x2 = Integer.parseInt(st.nextToken()) + 100;
            y2 = Integer.parseInt(st.nextToken()) + 100;
            visited = new boolean[2][201][201];
            System.out.printf("#%d %d\n", tc, bfs(x1, y1));
        }
    }

    public static int bfs(int startX, int startY) {
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(new Pair(startX, startY, 0, true));
        queue.offer(new Pair(startX, startY, 0, false));
        int result = 0;
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            if (!isValid(current.x, current.y)) {
                continue;
            }
            if (current.x == x2 && current.y == y2) {
                result = current.depth;
                break;
            }

            int dir = current.up ? 1 : 0;
            if (visited[dir][current.x][current.y]) continue;
            visited[dir][current.x][current.y] = true;
            if (current.up) {
                queue.offer(new Pair(current.x + 1, current.y, current.depth + 1, false));
                queue.offer(new Pair(current.x - 1, current.y, current.depth + 1, false));
            } else {
                queue.offer(new Pair(current.x, current.y + 1, current.depth + 1, true));
                queue.offer(new Pair(current.x, current.y - 1, current.depth + 1, true));
            }
        }
        return result;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x <= 200 && 0 <= y && y <= 200;
    }
}
