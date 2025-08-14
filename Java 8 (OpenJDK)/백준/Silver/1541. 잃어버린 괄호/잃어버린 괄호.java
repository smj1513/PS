
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Main {
    static int[] sums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] chunk = input.split("-");
        sums = new int[chunk.length];
        for (int i = 0; i < chunk.length; i++) {
            String[] numbers = chunk[i].split("\\+");
            int sum = 0;
            for (String num : numbers) {
                sum += Integer.parseInt(num);
            }
            sums[i] = sum;
        }
        int result = IntStream.of(sums).reduce((a, b) -> a - b).getAsInt();
        System.out.println(result);
    }
}
