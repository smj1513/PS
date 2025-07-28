
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    static final char[] alpha = {'A', 'C', 'G', 'T'};
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] SP = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int s = SP[0];
        int p = SP[1];
        String input = br.readLine();
        int[] origin = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        check = origin.clone();
        int[] current = new int[4];
        char[] window = input.substring(0, p).toCharArray();

        int cnt = 0;
        for (char c : window){
            current[toIdx(c)]++;
        }
        if(valid(current)) cnt++;
        for (int i = p; i < s; i++) {
            current[toIdx(input.charAt(i))]++;
            current[toIdx(input.charAt(i-p))]--;
            if (valid(current)) {
                cnt++;
            }

        }
        System.out.println(cnt);

    }

    public static boolean valid(int[] current){
        for(int i = 0 ; i < 4 ; i++){
            if(check[i] > current[i]){
                return false;
            }
        }
        return true;
    }

    public static int toIdx(char c) {
        int result = -1;
        switch (c) {
            case 'A':
                result = 0;
                break;
            case 'C':
                result = 1;
                break;
            case 'G':
                result = 2;
                break;
            case 'T':
                result = 3;
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }
}
