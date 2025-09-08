
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Block implements Comparable<Block> {
    int id;
    int width;
    int height;
    int weight;

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public Block(int id, int width, int height, int weight) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Block o) {
        return this.width - o.width;
    }
}

public class Main {
    static Block[] blocks;
    static int[] dp;
    static int N;
    static Stack<Integer> stack = new Stack<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        blocks = new Block[N + 1];
        blocks[0] = new Block(0, 0, 0, 0);
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            blocks[i] = new Block(i, width, height, weight);
        }
        dp = new int[N + 1];
        Arrays.sort(blocks);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (blocks[i].weight > blocks[j].weight) {
                    dp[i] = Math.max(dp[i], dp[j] + blocks[i].height);
                }
            }
            max = Math.max(max, dp[i]);
        }
        for (int i = N; i > 0; i--) {
            if (max == dp[i]) {
                stack.push(blocks[i].id);
                max -= blocks[i].height;
            }
        }
        System.out.println(stack.size());
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

}
