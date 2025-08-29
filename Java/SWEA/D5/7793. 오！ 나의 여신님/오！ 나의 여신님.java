
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static char[][] map;
    static boolean[][] visitedWater;
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
            visitedWater = new boolean[R][C];
            visited = new int[R][C];
            // 물 *, 돌X, 굴D, 고슴도치S, 빈곳 .
            List<int[]> waters = new ArrayList<>();
            int[] biber = null;
            for (int i = 0; i < R; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == '*') {
                        waters.add(new int[]{i, j});
                    } else if (map[i][j] == 'S') {
                        biber = new int[]{i, j};
                    }
                }
            }
            int max = bfs(waters, biber);
            System.out.println("#" + tc + " " + (max == -1 ? "GAME OVER" : max));
        }
    }

    public static int bfs(List<int[]> water, int[] biber) {
        Queue<int[]> waterQueue = new ArrayDeque<>(water);
        for (int[] point : water) {
            visitedWater[point[0]][point[1]] = true;
        }
        Queue<int[]> biberQueue = new ArrayDeque<>();
        biberQueue.add(biber);
        visited[biber[0]][biber[1]] = 0;
        while (!waterQueue.isEmpty() || !biberQueue.isEmpty()) {
            List<int[]> currentWaters = new ArrayList<>();
            List<int[]> currentBibers = new ArrayList<>();
            while (!waterQueue.isEmpty()) {
                currentWaters.add(waterQueue.poll());
            }
            while (!biberQueue.isEmpty()) {
                currentBibers.add(biberQueue.poll());
            }
            //물이 먼저 퍼짐
            for (int[] currentWater : currentWaters) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentWater[0];
                    int ny = dy[i] + currentWater[1];
                    if (isValid(nx, ny) && !visitedWater[nx][ny] &&
                            map[nx][ny] != 'X' &&
                            map[nx][ny] != 'D') {
                        waterQueue.add(new int[]{nx, ny});
                        visitedWater[nx][ny] = true;
                        map[nx][ny] = '*';
                    }
                }
            }
            //비버 이동
            for (int[] currentBiber : currentBibers) {
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + currentBiber[0];
                    int ny = dy[i] + currentBiber[1];
                    if (isValid(nx, ny) && visited[nx][ny] == 0 && map[nx][ny] != 'X' && map[nx][ny] != '*') {
                        if (map[nx][ny] == 'D') {
                            return visited[currentBiber[0]][currentBiber[1]] + 1;
                        }
                        visited[nx][ny] = visited[currentBiber[0]][currentBiber[1]] + 1;
                        biberQueue.add(new int[]{nx, ny});
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
