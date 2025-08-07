
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Position {
    int x;
    int y;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int distance(Position position) {
        return Math.abs(this.x - position.x) + Math.abs(this.y - position.y);
    }
}

public class Solution {
    static int result, N;
    static Position[] positions;
    static Position office, home;
    static boolean[] visited;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            visited = new boolean[N];
            positions = new Position[N];
            office = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            nums = new int[N];

            for (int i = 0; i < N; i++) {
                positions[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            result = Integer.MAX_VALUE;
            permutation(0);
            System.out.println("#" + tc + " " + result);
        }
    }

    public static void permutation(int depth) {
        if (depth == N) {
            int sum = 0;
            sum += office.distance(positions[nums[0]]);

            for(int i = 1 ; i < N; i++){
                sum += positions[nums[i]].distance(positions[nums[i-1]]);
            }
            sum += home.distance(positions[nums[N-1]]);
            result = Math.min(result, sum);
        } else {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    nums[depth] = i;
                    permutation(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
}
