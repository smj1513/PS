import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>(n);
            for (int j = 0 ; j < n ; j++){
                String[] input = br.readLine().split(" ");
                String type = input[1];
                map.merge(type, 1, Integer::sum);
            }
            int result = 1;
            for (Integer val : map.values()){
                result *= (val+1);
            }
            System.out.println(result-1);


        }
    }
}