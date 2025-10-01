import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int step = bfs(start, end);
        System.out.println(step);
    }

    public static int bfs(int start, int end) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(cur[0] == end){
                return cur[1];
            }
            int x = cur[0];
            int step = cur[1];
            int[][] next = {new int[]{2 * x, 0}, new int[]{x - 1, 1},  new int[]{x + 1, 1}};
            if (cur[0] > end) {
                if (!visited.contains(x - 1)) {
                    queue.offer(new int[]{x - 1, step + 1});
                    visited.add(x - 1);
                }
            } else {
                for (int[] nx : next) {
                    if (!visited.contains(nx[0])) {
                        queue.offer(new int[]{nx[0], step + nx[1]});
                        visited.add(nx[0]);
                    }
                }
            }
        }
        return -1;
    }
}
