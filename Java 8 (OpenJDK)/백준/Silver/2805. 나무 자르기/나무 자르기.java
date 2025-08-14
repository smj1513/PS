
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] trees;
    static int N, M;
    static int boundary = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        trees = new int[N];
        String[] f = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(f[i]);
            max = Math.max(trees[i], max);
        }

        while (boundary < max) {
            int mid = (boundary + max) / 2;
            long sum = 0;
            for (int tree : trees) {
                //나무가 잘렸다면? -> 나무의 높이가 톱의 높이보다 높은 경우
                if (tree - mid > 0) {
                    sum += (tree - mid);
                }
            }

            if (sum < M) { //잘린 나무의 합이 목표치보다 낮다면?
                //톱의 높이를 낮춰야함.
                max = mid;
            } else { //
                // 잘린 나무의 합이 목표치 보다 높다면?
                //톱의 높이를 높여야함.
                boundary = mid + 1;
            }
        }

        System.out.println(boundary - 1);

    }
}
