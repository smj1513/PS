
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/*
* 조합 문제
* */
public class Main {
    static int[] operator;
    static int[] operand;
    static int N;
    static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operand = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        operator = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        calc(operand[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    public static void calc(long num, int depth) {
        if (depth == N) {
            max = Math.max(num, max);
            min = Math.min(num, min);
            return;
        } else {
            for (int i = 0; i < 4; i++) {
                if (operator[i] > 0) {
                    operator[i]--;
                    switch (i) {
                        case 0:
                            calc(num + operand[depth], depth + 1);
                            break;
                        case 1:
                            calc(num - operand[depth], depth + 1);
                            break;
                        case 2:
                            calc(num * operand[depth], depth + 1);
                            break;
                        case 3:
                            calc(num / operand[depth], depth + 1);
                            break;
                    }
                    operator[i]++;
                }
            }
        }
    }
}
