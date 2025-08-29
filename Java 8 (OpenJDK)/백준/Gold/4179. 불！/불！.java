
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visitedFire;
    static int[][] visited;
    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visitedFire = new boolean[R][C];
        visited = new int[R][C];
        List<int[]> fires = new ArrayList<>();
        int[] jihun = null;
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') {
                    fires.add(new int[]{i, j});
                } else if (map[i][j] == 'J') {
                    jihun = new int[]{i, j};
                }
            }
        }
        int max = bfs(fires, jihun);
        System.out.println(max == -1 ? "IMPOSSIBLE" : max);
    }


    public static int bfs(List<int[]> fire, int[] jihun) {
        Queue<int[]> fireQueue = new ArrayDeque<>(fire);
        for (int[] point : fire) {
            visitedFire[point[0]][point[1]] = true;
        }
        Queue<int[]> jihunQueue = new ArrayDeque<>();
        jihunQueue.add(jihun);
        visited[jihun[0]][jihun[1]] = 0;
        while (!fireQueue.isEmpty() || !jihunQueue.isEmpty()) {
            List<int[]> currentFires = new ArrayList<>();
            List<int[]> currentJihuns = new ArrayList<>();
            while (!fireQueue.isEmpty()) {
                currentFires.add(fireQueue.poll());
            }
            while (!jihunQueue.isEmpty()) {
                currentJihuns.add(jihunQueue.poll());
            }

            //불이 퍼짐
            for (int[] currentFire : currentFires) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentFire[0];
                    int ny = dy[i] + currentFire[1];
                    if (isValid(nx, ny) && !visitedFire[nx][ny] &&
                            map[nx][ny] != '#') {
                        fireQueue.add(new int[]{nx, ny});
                        visitedFire[nx][ny] = true;
                        map[nx][ny] = 'F';
                    }
                }
            }
            //지훈이동
            for (int[] currentJihun : currentJihuns) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentJihun[0];
                    int ny = dy[i] + currentJihun[1];
                    if (nx == -1 || nx == R || ny == -1 || ny == C) {
                        return visited[currentJihun[0]][currentJihun[1]] + 1;
                    }
                    if (isValid(nx, ny) && visited[nx][ny] == 0 && map[nx][ny] != '#' && map[nx][ny] != 'F') {
                        visited[nx][ny] = visited[currentJihun[0]][currentJihun[1]] + 1;
                        jihunQueue.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return -1;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
