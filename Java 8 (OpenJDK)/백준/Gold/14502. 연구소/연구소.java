
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static List<Point> safeZone = new ArrayList<>();
    static List<Point> virus = new ArrayList<>();
    static int[][] map;
    static int N, M;
    static Point[] temp = new Point[3];
    static int maxSafe = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    safeZone.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }
        combination(0, 0);
        System.out.println(maxSafe);
    }

    static void combination(int start, int depth) {
        if (depth == 3) {//벽세우기
            bfs(temp);
        } else {
            for (int i = start; i < safeZone.size(); i++) {
                temp[depth] = safeZone.get(i);
                combination(i + 1, depth + 1);
            }
        }
    }

    static void bfs(Point[] addWalls) {
        Queue<Point> queue = new ArrayDeque<>(virus);
        boolean[][] visited = new boolean[N][M];
        int[][] cloneMap = new int[N][];
        for (int i = 0 ; i < N;i++){
            cloneMap[i] = map[i].clone();
        }
        for (Point vi : virus) {
            visited[vi.x][vi.y] = true;
        }
        for (Point addwall : addWalls) {
            cloneMap[addwall.x][addwall.y] = 1;
        }
        int safezones = 0;
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny] && cloneMap[nx][ny] != 1) {
                    cloneMap[nx][ny] = 2;
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        for (int[] a : cloneMap) {
            for (int b : a) {
                if (b == 0) {
                    safezones++;
                }
            }
        }
        maxSafe = Math.max(maxSafe, safezones);
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
