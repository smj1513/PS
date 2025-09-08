
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// Block 클래스는 이전과 동일합니다.
class Block implements Comparable<Block> {
    int id;
    int width;
    int height;
    int weight;

    public Block(int id, int width, int height, int weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Block o) {
        // 밑면 넓이를 기준으로 오름차순 정렬 (DP 적용을 위해)
        return this.width - o.width;
    }
}

public class Main {
    static Block[] blocks;
    static int N;
    static int[] dp;   // 메모이제이션을 위한 배열 (dp[i] = i번째 블록 위에 쌓을 수 있는 최대 높이)
    static int[] path; // 경로 추적을 위한 배열

    /**
     * prevBlockIndex에 해당하는 블록 위에 쌓을 수 있는 탑의 최대 높이를 반환하는 재귀 함수
     * @param prevBlockIndex 현재 탑의 가장 위 블록의 인덱스
     * @return prevBlockIndex 블록 위에 추가로 쌓을 수 있는 높이의 최댓값
     */
    private static int solve(int prevBlockIndex) {
        // 이미 계산한 문제라면 저장된 값을 반환 (메모이제이션)
        if (dp[prevBlockIndex] != -1) {
            return dp[prevBlockIndex];
        }

        int maxHeightOnTop = 0;
        // 기본적으로는 더 이상 쌓지 못하므로 다음 블록은 없음 (-1)
        path[prevBlockIndex] = -1; 

        Block prevBlock = blocks[prevBlockIndex];

        // 다음으로 올릴 블록을 찾는다.
        // 정렬했으므로 prevBlockIndex 다음부터만 탐색해도 됨
        for (int i = prevBlockIndex + 1; i < N; i++) {
            // 이전 블록 위에 현재 블록(blocks[i])을 올릴 수 있다면
            if (blocks[i].weight > prevBlock.weight) {
                // 현재 블록을 올렸을 때의 총 높이 = (현재 블록 높이 + 그 위에 쌓을 수 있는 최대 높이)
                int currentTotalHeight = blocks[i].height + solve(i);

                // 기존의 최대 높이보다 더 높게 쌓을 수 있다면 갱신
                if (currentTotalHeight > maxHeightOnTop) {
                    maxHeightOnTop = currentTotalHeight;
                    path[prevBlockIndex] = i; // 경로 갱신: prevBlock 위에는 i 블록을 올리는 것이 최적
                }
            }
        }

        // 계산된 결과를 저장하고 반환
        return dp[prevBlockIndex] = maxHeightOnTop;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        blocks = new Block[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            blocks[i] = new Block(i + 1, width, height, weight);
        }

        // 밑면 넓이 기준 오름차순 정렬 (탐색 최적화)
        Arrays.sort(blocks);
        
        // dp와 path 배열 초기화
        dp = new int[N];
        path = new int[N];
        Arrays.fill(dp, -1); // -1은 아직 계산되지 않았음을 의미

        int maxHeight = 0;
        int startIndex = -1; // 가장 높은 탑의 시작 블록 인덱스

        // 각 블록을 탑의 가장 아래에 두는 경우를 모두 시도
        for (int i = 0; i < N; i++) {
            int currentTotalHeight = blocks[i].height + solve(i);
            if (currentTotalHeight > maxHeight) {
                maxHeight = currentTotalHeight;
                startIndex = i;
            }
        }
        
        List<Integer> resultPath = new ArrayList<>();
        while(startIndex != -1) {
            resultPath.add(blocks[startIndex].id);
            startIndex = path[startIndex];
        }

        // 결과 출력
        System.out.println(resultPath.size());
        for (Integer id : resultPath) {
            System.out.println(id);
        }
    }
}