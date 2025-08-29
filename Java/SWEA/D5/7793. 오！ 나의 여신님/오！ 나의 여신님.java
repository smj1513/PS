
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static char[][] map;
    static boolean[][] visitedDemon;
    static int[][] visited;
    static int R, C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new char[R][C];
            visitedDemon = new boolean[R][C];
            visited = new int[R][C];
            // 악마*, 돌X, 여신D, 수연S, 빈곳.
            List<int[]> demon = new ArrayList<>();
            int[] suyan = null;
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '*') {
                        demon.add(new int[]{i, j});
                    } else if (map[i][j] == 'S') {
                        suyan = new int[]{i, j};
                    }
                }
            }
            int max = bfs(demon, suyan);
            System.out.println("#" + tc + " " + (max == -1 ? "GAME OVER" : max));
        }
    }

    public static int bfs(List<int[]> demon, int[] suyan) {
        Queue<int[]> demonQueue = new ArrayDeque<>(demon);
        for (int[] point : demon) {
            visitedDemon[point[0]][point[1]] = true;
        }
        Queue<int[]> suyanQueue = new ArrayDeque<>();
        suyanQueue.add(suyan);
        visited[suyan[0]][suyan[1]] = 0;
        while (!demonQueue.isEmpty() || !suyanQueue.isEmpty()) {
            List<int[]> currentDemon = new ArrayList<>();
            List<int[]> currentSuyan = new ArrayList<>();
            while (!demonQueue.isEmpty()) {
                currentDemon.add(demonQueue.poll());
            }
            while (!suyanQueue.isEmpty()) {
                currentSuyan.add(suyanQueue.poll());
            }
            //물이 먼저 퍼짐
            for (int[] currentDe : currentDemon) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentDe[0];
                    int ny = dy[i] + currentDe[1];
                    if (isValid(nx, ny) && !visitedDemon[nx][ny] &&
                            map[nx][ny] != 'X' &&
                            map[nx][ny] != 'D') {
                        demonQueue.add(new int[]{nx, ny});
                        visitedDemon[nx][ny] = true;
                        map[nx][ny] = '*';
                    }
                }
            }
            //비버 이동
            for (int[] currentsu : currentSuyan) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentsu[0];
                    int ny = dy[i] + currentsu[1];
                    if (isValid(nx, ny) && visited[nx][ny] == 0 && map[nx][ny] != 'X' && map[nx][ny] != '*') {
                        if (map[nx][ny] == 'D') {
                            return visited[currentsu[0]][currentsu[1]] + 1;
                        }
                        visited[nx][ny] = visited[currentsu[0]][currentsu[1]] + 1;
                        suyanQueue.add(new int[]{nx, ny});
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