
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H;
    static int min;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];
            int totalBricks = 0;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) totalBricks++;
                }
            }

            min = totalBricks; // 초기 최솟값은 전체 벽돌 수
            solve(0, map);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    // depth: 현재까지 떨어뜨린 구슬의 수, map: 현재 상태의 벽돌 맵
    private static void solve(int depth, int[][] map) {
        // [가지치기 1] 이미 벽돌을 모두 부쉈다면 더 이상 탐색할 필요 없음
        if (min == 0) {
            return;
        }

        // N개의 구슬을 모두 사용했다면 종료
        if (depth == N) {
            // 남은 벽돌 수 계산 후 최솟값 갱신
            int count = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] > 0) count++;
                }
            }
            min = Math.min(min, count);
            return;
        }

        // 모든 열(W)에 대해 구슬을 떨어뜨리는 경우를 시도
        for (int c = 0; c < W; c++) {
            // 1. 현재 맵 상태를 복사하여 다음 상태를 준비
            int[][] nextMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                nextMap[i] = map[i].clone();
            }

            // 2. 해당 열에서 가장 위의 벽돌 찾기
            int r = -1;
            for (int i = 0; i < H; i++) {
                if (nextMap[i][c] > 0) {
                    r = i;
                    break;
                }
            }

            // 벽돌이 없는 열이면 건너뛰기
            if (r == -1) {
                // [가지치기 2] 이 열에 쏠 수 없다면, 남은 구슬을 다 써도 현재 맵 상태와 같음
                // 그냥 현재 맵 상태로 마지막 depth까지 진행한 것과 동일한 결과
                solve(N, nextMap);
                continue;
            }

            // 3. 벽돌 부수기 및 재정렬
            breakBricks(nextMap, r, c);
            down(nextMap);

            // 4. 변경된 맵으로 다음 구슬 쏘기 (재귀 호출)
            solve(depth + 1, nextMap);
        }
    }

    private static void breakBricks(int[][] map, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        if (map[r][c] > 0) {
            queue.offer(new int[]{r, c, map[r][c]});
            map[r][c] = 0;
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];
            int power = current[2];

            for (int d = 0; d < 4; d++) {
                for (int p = 1; p < power; p++) {
                    int nr = curR + dr[d] * p;
                    int nc = curC + dc[d] * p;

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
                        queue.offer(new int[]{nr, nc, map[nr][nc]});
                        map[nr][nc] = 0;
                    }
                }
            }
        }
    }

    public static void down(int[][] map) {
        for (int c = 0; c < W; c++) {
            Queue<Integer> bricks = new LinkedList<>();
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] > 0) {
                    bricks.offer(map[r][c]);
                }
                map[r][c] = 0;
            }
            int r = H - 1;
            while (!bricks.isEmpty()) {
                map[r--][c] = bricks.poll();
            }
        }
    }
}