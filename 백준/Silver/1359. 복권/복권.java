

import java.util.Scanner;

public class Main {
    static int N, M, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        double probability = 0.0;
        for (int i = K; i <= M; i++) {
            probability += (combination(M, i) * combination(N - M, M - i));
        }
        System.out.println(probability / combination(N, M));
    }

    public static double combination(int n, int r) {
        double result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }
}
