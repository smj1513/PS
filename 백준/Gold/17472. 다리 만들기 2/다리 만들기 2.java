
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

class Edge implements Comparable<Edge> {
    int start, end, distance;

    public Edge(int start, int end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge o) {
        return this.distance - o.distance;
    }
}

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static List<Edge> edges = new ArrayList<>();
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;

        //섬 구분 짓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    bfs(i, j, cnt++);
                }
            }
        }
        //다리 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    makeEdge(i, j, map[i][j]);
                }
            }
        }
        parents = new int[cnt];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }
        int result = 0;
        Collections.sort(edges);
        for (Edge e : edges) {
            if (union(e.start, e.end)) {
                result += e.distance;
            }
        }
        int landcnt = 0;
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == i) {
                landcnt++;
            }
        }
        if (landcnt > 1) {
            System.out.println(-1);
        } else {
            System.out.println(result == 0 ? -1 : result);
        }
    }

    public static boolean union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            parents[fb] = parents[fa];
            return true;
        } else {
            return false;
        }
    }

    public static int find(int a) {
        if (parents[a] == a) {
            return a;
        } else {
            return parents[a] = find(parents[a]);
        }
    }

    public static void bfs(int x, int y, int cnt) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y));
        map[x][y] = cnt;
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
                    map[nx][ny] = cnt;
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    public static void makeEdge(int x, int y, int islandNum) {
        for (int i = 0; i < 4; i++) {
            int len = 0;
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (isValid(nx, ny) && map[nx][ny] == 0) {
                //같은 섬 방향으로는 다리를 세우지 않는다
                if (map[nx][ny] != islandNum) {
                    len++;
                    nx += dx[i];
                    ny += dy[i];
                }
            }
            if (isValid(nx, ny) && len > 1) {
                edges.add(new Edge(map[x][y], map[nx][ny], len));
                break;
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
