
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();
        int time = bfs(start, end);
        System.out.println(time);
    }

    static int bfs(int start, int end) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100001];
        visited[start] = true;
        queue.add(new int[]{start, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[0] == end) {
                return current[1];
            }
            if (valid(current[0] - 1, visited.length) && !visited[current[0] - 1]) {
                queue.add(new int[]{current[0] - 1, current[1] + 1});
                visited[current[0] - 1] = true;
            }
            if (valid(current[0] + 1, visited.length) && !visited[current[0] + 1]) {
                queue.add(new int[]{current[0] + 1, current[1] + 1});
                visited[current[0] + 1] = true;
            }
            if (valid(current[0] * 2, visited.length) && !visited[current[0] * 2]) {
                queue.add(new int[]{current[0] * 2, current[1] + 1});
                visited[current[0] * 2] = true;
            }
        }
        return -1;
    }

    static boolean valid(int n, int range) {
        return 0 <= n && n < range;
    }
}
