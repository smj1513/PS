
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Cell implements Comparable<Cell> {
    int x, y;
    int lifePower;
    int timer;
    int status; // -1 : 죽음, 0: 비활성, 1 활성

    public Cell(int x, int y, int lifePower) {
        this.x = x;
        this.y = y;
        this.lifePower = lifePower;
        this.timer = 0;
        this.status = 0;
    }

    void timeFlow() {
        if (status == 0) {
            activating();
        } else if (status == 1) {
            deActivating();
        }
    }

    void activating() {
        if (++timer == lifePower) {
            status = 1;
        }
    }

    void deActivating() {
        if (--timer == 0) {
            status = -1;
        }
    }

    @Override
    public int compareTo(Cell o) {
        return o.lifePower - this.lifePower;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", lifePower=" + lifePower +
                ", timer=" + timer +
                ", status=" + status +
                '}';
    }
}

public class Solution {
    static Cell[][] greed;
    static int N, M, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            greed = new Cell[500][500];

            PriorityQueue<Cell> pq = new PriorityQueue<>();
            for (int i = 250 - N; i < 250; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 250 - M; j < 250; j++) {
                    int N = Integer.parseInt(st.nextToken());
                    if (N != 0) {
                        greed[i][j] = new Cell(i, j, N);
                        pq.offer(greed[i][j]);

                    }
                }
            }
            PriorityQueue<Cell> prev = pq;
            for (int i = 0; i < K; i++) {
                prev = bfs(prev);
            }
            System.out.println("#" + tc + " " + prev.size());

        }
    }

    public static PriorityQueue<Cell> bfs(PriorityQueue<Cell> pq) {
        PriorityQueue<Cell> nextPq = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            if (cur.status == 1) {
                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (greed[nx][ny] == null) {
                        greed[nx][ny] = new Cell(nx, ny, cur.lifePower);
                        nextPq.offer(greed[nx][ny]);
                    }
                }
            }
            cur.timeFlow();
            if (cur.status == 0 || cur.status == 1) {
                nextPq.offer(cur);
            }

        }
        return nextPq;
    }
}
