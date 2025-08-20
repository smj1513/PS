
import java.util.*;

public class Main {
    static final int MAX_POS = 100000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();

        // 수빈이가 동생보다 뒤에 있거나 같은 위치에 있는 경우
        if (start >= end) {
            System.out.println(start - end);
            System.out.println(1);
            return;
        }

        bfs(start, end);
        sc.close();
    }

    static void bfs(int start, int end) {
        // visited 배열에 각 위치까지 걸리는 최단 시간을 저장
        int[] visited = new int[MAX_POS + 1];
        Arrays.fill(visited, Integer.MAX_VALUE); // 충분히 큰 값으로 초기화

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = 0;

        int minTime = Integer.MAX_VALUE;
        int count = 0;

        while (!queue.isEmpty()) {
            int currentPos = queue.poll();
            int currentTime = visited[currentPos];

            // 이미 찾은 최단 시간보다 더 오래 걸리는 경로는 탐색 중단
            if (currentTime > minTime) {
                break;
            }

            //목적지에 도달한 경우
            if (currentPos == end) {
                minTime = currentTime; // 최단 시간 기록
                count++;               // 방법의 수 증가
                continue;              // 목적지에서는 더 이상 탐색할 필요 없음
            }
            int[] nextPositions = {currentPos - 1, currentPos + 1, currentPos * 2};
            for (int nextPos : nextPositions) {
                //핵심 로직: 다음 위치를 방문할 최단 경로이거나, 최단 경로와 같은 시간의 다른 경로일 경우에만 큐에 추가
                if (isValid(nextPos) && visited[nextPos] >= currentTime + 1) {
                    visited[nextPos] = currentTime + 1;
                    queue.add(nextPos);
                }
            }
        }
        System.out.println(minTime);
        System.out.println(count);
    }

    static boolean isValid(int n) {
        return n >= 0 && n <= MAX_POS;
    }
}

