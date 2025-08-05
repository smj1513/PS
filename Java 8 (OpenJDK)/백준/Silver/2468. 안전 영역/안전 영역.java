
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    static int[][] graph;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static int n;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i <= 100 ;i ++){
            int cnt = 0;
            for (int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(graph[j][k] > i && !visited[j][k] ){
                        dfs(j, k, i);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
            visited = new boolean[n][n];
        }

        System.out.println(result);

    }

    public static void dfs(int x, int y, int boundary) {
        visited[x][y] = true;
        for(int i = 0 ; i < 4 ; i++){
            int drx = dx[i] + x;
            int dry = dy[i] + y;
            if(valid(drx,dry) && !visited[drx][dry] && graph[drx][dry] > boundary){
                visited[drx][dry] = true;
                dfs(drx, dry, boundary);
            }
        }
    }

    public static boolean valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
