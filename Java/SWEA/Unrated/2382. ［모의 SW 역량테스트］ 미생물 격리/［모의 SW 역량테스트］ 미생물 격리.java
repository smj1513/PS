
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Micro {
    int x, y, cnt, dir, total; //total : 군집이 합쳐지는 상황의 미생물 수
    boolean isDead;

    public Micro(int x, int y, int cnt, int dir) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.dir = dir;
        this.isDead = false;
        this.total = cnt;
    }
}

public class Solution {
    static int N, M, K;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};
    static Micro[] list;
    static Micro[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            list = new Micro[K];
            map = new Micro[N][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                list[i] = new Micro(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                );
            }
            int result = move();
            System.out.println("#" + tc + " " + result);
        }
    }

    public static int move() {
        int time = M, nr, nc, remainCnt = 0;
        while (time-- > 0) {
            for (Micro cur : list) {
                if (cur.isDead) continue;
                //한 칸 이동
                nr = cur.x += dx[cur.dir];
                nc = cur.y += dy[cur.dir];
                //약품 칸 처리
                if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
                    //군집의 크기 절반으로 줄이고 방향 바꿈, 크기가 0이면 소멸
                    cur.total = cur.cnt = cur.cnt / 2;
                    if (cur.cnt == 0) {//군집 소멸
                        cur.isDead = true;
                        continue;
                    }
                    //소멸된 군집이 아닌 경우
                    if (cur.dir % 2 == 0) {
                        cur.dir--;
                    } else {
                        cur.dir++;
                    }
                }
                //군집 병합
                if (map[nr][nc] == null) { //그 셀에 처음 도착하는 군집
                    map[nr][nc] = cur;
                } else {
                    //
                    if (map[nr][nc].cnt > cur.cnt) {
                        map[nr][nc].total += cur.cnt;
                        cur.isDead = true;
                    } else {
                        //나중에 도착한 군집의 크기가 큰 경우
                        cur.total += map[nr][nc].total;
                        map[nr][nc].isDead = true;
                        map[nr][nc] = cur;
                    }
                }

            }//end for : 군집 리스트 처리
            remainCnt = reset(); // 현재 시간에 이동한 군집들 정리 후 살아 있는 미생물 수 받기
        }// end while : 시간 반복

        return remainCnt;
    }

    private static int reset() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != null) {
                    map[i][j].cnt = map[i][j].total;
                    total += map[i][j].cnt;
                    map[i][j] = null;
                }
            }
        }
        return total;
    }
}
