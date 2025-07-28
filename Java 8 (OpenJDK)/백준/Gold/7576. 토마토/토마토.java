
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] tomato;
    static boolean[][] visited;
    static int N, M;
    static List<int[]> startPoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] mn = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = mn[0];
        N = mn[1];
        tomato = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String[] inputs = br.readLine().split(" ");
            tomato[i] = new int[M];
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(inputs[j]);
                if (tomato[i][j] == 1) {
                    startPoints.add(new int[]{i, j});
                }
            }
        }
        bfs();
        int max = -1;
        for(int[] arr: tomato){
            for(int a : arr){
                if(a == 0){
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, a);
            }
        }
        if(max == 1){
            System.out.println(0);
        }else {
            System.out.println(max - 1);
        }
    }

    public static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        for(int[] startPoint : startPoints){
            queue.add(new int[]{startPoint[0], startPoint[1]});
            visited[startPoint[0]][startPoint[1]] = true;
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int drx = current[0] + dx[i];
                int dry = current[1] + dy[i];
                if (valid(drx, dry) && !visited[drx][dry] && tomato[drx][dry] != -1) {
                    visited[drx][dry] = true;
                    tomato[drx][dry] = tomato[current[0]][current[1]] + 1;
                    queue.add(new int[]{drx, dry});
                }
            }
        }
    }

    public static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
