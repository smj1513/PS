
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long left = 0;
    static long right = 0; //입력받은 lan선중 가장 짧은 길이
    static long[] lan;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        lan = new long[K];
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, lan[i]);
        }
        right++;
        parametricSearch(N);
        System.out.println(left - 1);
    }

    public static void parametricSearch(long N) {

        while (left < right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            //구해진 중간길이로 잘라서 총 몇개가 만들어지는가?
            for (int i = 0; i < lan.length; i++) {
                cnt += (lan[i] / mid);
            }

            /**
             * upper bound
             * 중간 길이로 잘랐을 때의 개수가 만들고자 하는 랜선의 개수보다 작다면
             * 자르고자 하는 길이를 줄이기 위해 최대 길이를 줄인다
             * 그 외에는 자르고자 하는 길이를 늘려야 함 최소 길이를 늘린다.
             * */
            if (cnt < N) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }
}
