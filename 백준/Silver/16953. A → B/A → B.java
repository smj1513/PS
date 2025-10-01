
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int cnt = 1;
        while (B != A) {
            if (B < A) {
                System.out.println("-1");
                return;
            }
            String s = String.valueOf(B);
            char last = s.charAt(s.length() - 1);
            if (s.length() >= 2 && last == '1') {
                B = Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (B % 2 == 0) {
                B /= 2;
            } else {
                System.out.println("-1");
                return;
            }
            cnt++;
        }
        System.out.println(cnt);

    }
}
