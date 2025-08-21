
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] origin;
    static char[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        origin = new char[C];
        temp = new char[L];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            origin[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(origin);
        combination(0,0 );
    }

    public static void combination(int start, int depth) {
        if (depth == L) {
            if(isValid()){
                System.out.println(temp);
            }
            return;
        } else {
            for (int i = start; i < C; i++) {
                temp[depth] = origin[i];
                combination(i + 1, depth + 1);
            }
        }
    }

    public static boolean isValid() {
        int vowels = 0;
        int consonants = 0;

        for (char x : temp) {
            if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                vowels++;
            } else {
                consonants++;
            }
        }

        return vowels >= 1 && consonants >= 2;
    }
}
