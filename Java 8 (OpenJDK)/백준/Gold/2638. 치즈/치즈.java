
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int cheeseCount;
    static int[] dr = {-1, 1, 0, 0}; // row (행) 이동
    static int[] dc = {0, 0, -1, 1}; // column (열) 이동
    static boolean[][] visited;

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
                if (map[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }

        int time = 0;
        while (cheeseCount > 0) {
            time++;
            findOutsideAir(); // 1. 외부 공기를 먼저 모두 찾는다.
            meltCheese();     // 2. 외부 공기와 닿은 치즈를 녹인다.
        }
        System.out.println(time);
    }

    /**
     * BFS를 이용해 (0,0)에서 시작하는 외부 공기를 모두 탐색하고 visited 배열에 표시합니다.
     */
    public static void findOutsideAir() {
        visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        map[0][0] = -1; // 외부 공기를 -1로 표시하여 다음 탐색에서 재방문하지 않도록 함

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] != 1) {
                    visited[nr][nc] = true;
                    map[nr][nc] = -1; // 외부 공기 표시
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    /**
     * 전체 맵을 순회하며 외부 공기(visited == true)와 2변 이상 맞닿은 치즈를 찾아 녹입니다.
     */
    public static void meltCheese() {
        List<int[]> toMelt = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) { // 현재 위치가 치즈라면
                    int exposedSides = 0;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dr[k];
                        int nj = j + dc[k];
                        // 인접한 칸이 외부 공기(-1)라면
                        if (map[ni][nj] == -1) {
                            exposedSides++;
                        }
                    }

                    if (exposedSides >= 2) {
                        toMelt.add(new int[]{i, j});
                    }
                }
            }
        }

        // 녹을 치즈들을 한 번에 처리
        for (int[] pos : toMelt) {
            map[pos[0]][pos[1]] = 0; // 치즈를 공기로 변경
            cheeseCount--;
        }
    }
}