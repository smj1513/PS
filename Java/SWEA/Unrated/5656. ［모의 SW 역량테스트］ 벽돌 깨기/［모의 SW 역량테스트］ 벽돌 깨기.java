
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, W, H;
    static int min;

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
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            // 중복 순열로 구슬을 떨어뜨릴 위치를 모두 탐색
            generateDropSequence(0, new int[N], map);

            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    // 중복 순열 생성 (구슬을 떨어뜨릴 열의 모든 경우의 수)
    public static void generateDropSequence(int depth, int[] sequence, int[][] map) {
        if (depth == N) {
            play(sequence, map);
            return;
        }

        for (int i = 0; i < W; i++) {
            sequence[depth] = i; // i번째 열에 구슬을 떨어뜨림
            generateDropSequence(depth + 1, sequence, map);
        }
    }

    // 시뮬레이션 실행
    private static void play(int[] sequence, int[][] originalMap) {
        // 원본 맵을 변경하지 않기 위해 깊은 복사
        int[][] tempMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            tempMap[i] = originalMap[i].clone();
        }

        // N번의 구슬을 순서대로 떨어뜨림
        for (int col : sequence) {
            // 1. 구슬에 맞는 벽돌 찾기
            int row = -1;
            for (int r = 0; r < H; r++) {
                if (tempMap[r][col] != 0) {
                    row = r;
                    break;
                }
            }

            // 해당 열에 벽돌이 없으면 다음 구슬로 넘어감
            if (row == -1) continue;

            // 2. 벽돌 부수기 (연쇄 작용 포함)
            breakBricks(tempMap, row, col);

            // 3. 빈 공간 정리 (벽돌 내리기)
            down(tempMap);
        }

        // 남은 벽돌 개수 계산
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (tempMap[i][j] > 0) {
                    count++;
                }
            }
        }
        // 최솟값 갱신
        min = Math.min(min, count);
    }

    // BFS를 이용한 벽돌 부수기
    private static void breakBricks(int[][] map, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c, map[r][c]});
        map[r][c] = 0; // 방문 처리 (폭발했으므로 0으로)

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];
            int power = current[2];

            // 4방향으로 power-1 만큼 연쇄 폭발
            for (int d = 0; d < 4; d++) {
                for (int p = 1; p < power; p++) {
                    int nr = curR + dr[d] * p;
                    int nc = curC + dc[d] * p;

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
                        queue.offer(new int[]{nr, nc, map[nr][nc]});
                        map[nr][nc] = 0; // 연쇄 폭발할 벽돌도 바로 0으로 처리
                    }
                }
            }
        }
    }

    // 벽돌 아래로 내리기
    public static void down(int[][] map) {
        for (int c = 0; c < W; c++) {
            Queue<Integer> bricks = new LinkedList<>();
            // 아래에서부터 벽돌을 큐에 저장
            for (int r = H - 1; r >= 0; r--) {
                if (map[r][c] != 0) {
                    bricks.offer(map[r][c]);
                }
            }

            // 해당 열을 0으로 초기화
            for (int r = 0; r < H; r++) {
                map[r][c] = 0;
            }

            // 큐에서 꺼내어 아래부터 채우기
            int r = H - 1;
            while (!bricks.isEmpty()) {
                map[r--][c] = bricks.poll();
            }
        }
    }
}