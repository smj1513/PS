import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 모든 행에 대해 해당 행이 1로 채워지거나 0으로 채워 지거나의 부분집합으로 접근해보자..
 * 행의 최대 개수는 13개..
 * 테스트 케이스 50개를 합쳐 Java로 5초 -> 테케당 0.1초
 * 조합으로 접근시 O(2^13 * 20) -> 시간 초과.
 * 백트래킹이 필수적이다.
 * 현재 약품을 투약하는 경우의 수에 대한 체크 함수는 따로 만들고 접근해보자.
 * */

public class Solution {
    static int[][] film;
    static int D, W, K;
    static int[][] drugs;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            min = Integer.MAX_VALUE;
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            drugs = new int[2][];

            film = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (test()) {
                System.out.println("#" + tc + " " + 0);
                continue;
            }
            for (int i = 0; i < 2; i++) {
                drugs[i] = new int[W];
                Arrays.fill(drugs[i], i);
            }
            subSet(0, 0);
            System.out.println("#" + tc + " " + min);
        }
    }
    // row: 현재 결정할 행, count: 현재까지 약물 투입 횟수
    public static void subSet(int row, int count) {
        // 가지치기: 현재 투입 횟수가 이미 찾은 최소값보다 크면 더 볼 필요 없음
        if (count >= min) {
            return;
        }

        // 기저 조건: 모든 행의 처리를 결정했을 때
        if (row == D) {
            if (test()) { // 성능 검사를 통과하면
                min = Math.min(min, count); // 최소값 갱신
            }
            return;
        }

        // 1. 약물 미투입
        subSet(row + 1, count);

        // 2. A(0) 약물 투입
        int[] originalRow = film[row]; // 원래 행 상태 저장
        film[row] = drugs[0];
        Arrays.fill(film[row], 0); // A 약물 투입
        subSet(row + 1, count + 1);

        film[row] = drugs[1];
        subSet(row + 1, count + 1);
        film[row] = originalRow; // 행 상태 복구 (백트래킹)
    }
    //성능 평가 함수
    public static boolean test() {
        for (int j = 0; j < W; j++) { // 열(column) 우선 탐색
            int max_streak = 0;
            int current_streak = 1;
            for (int i = 1; i < D; i++) { // 행(row)을 위에서 아래로
                if (film[i][j] == film[i - 1][j]) {
                    current_streak++;
                } else {
                    // 연속이 끊겼을 때, 이전까지의 연속 길이를 최대값과 비교
                    max_streak = Math.max(max_streak, current_streak);
                    // 현재 셀부터 다시 1로 시작
                    current_streak = 1;
                }
            }
            // 루프가 끝난 후 마지막 연속 길이(current_streak)도 최대값과 비교해야 함
            max_streak = Math.max(max_streak, current_streak);

            // 해당 열이 K 기준을 통과 못했다면, 더 볼 필요 없이 즉시 실패
            if (max_streak < K) {
                return false;
            }
        }
        // 모든 열이 기준을 통과했으면 성공
        return true;
    }
}
