
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_SIZE = 1_000_000;
    static boolean[] isPrime = new boolean[MAX_SIZE + 1];
    static List<Integer> primeList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sieve();

        int N = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < N; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int startIndex = Collections.binarySearch(primeList, a);
            if (startIndex < 0) {
                startIndex = -(startIndex + 1);
            }

            int endIndex = Collections.binarySearch(primeList, b);
            if (endIndex < 0) {
                endIndex = -(endIndex + 1) - 1;
            }

            if (startIndex >= primeList.size() || startIndex > endIndex) {
                System.out.println(-1);
                continue;
            }

            int count = endIndex - startIndex + 1;

            if (count % 2 == 0) { 
                System.out.println(-1);
            } else {
                int medianIndex = startIndex + (count / 2);
                System.out.println(primeList.get(medianIndex));
            }
        }
    }

    static void sieve() {
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX_SIZE; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX_SIZE; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= MAX_SIZE; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
    }
}