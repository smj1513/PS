import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] nums = new String[N];
        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }
        int k = 1;
        for (k = 1; k < 100; k++) {
            Set<String> set = new HashSet<>();
            for (String num : nums){
                set.add(num.substring(num.length() - k));
            }
            if(set.size() == nums.length){
                break;
            }
        }
        System.out.println(k);
    }
}
