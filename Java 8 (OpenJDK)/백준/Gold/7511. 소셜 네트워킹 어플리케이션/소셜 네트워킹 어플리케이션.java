
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * 분리집합을 사용하는 기본적인 문제
 * 먼저 주어지는 k개의 (a, b)쌍에 대해 union 연산을 통해 서로소 집합을 생성(union)
 * 이후 주어지는 m개의 (u, v)쌍에 대하여 find 연산으로 두 쌍이 같은 집합에 포함되어 있는지 확인
 * 스트림으로 입출력하면 메모리초과남
 * */
public class Main {
    static int[] user;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            user = new int[n];
            for (int i = 0; i < n; i++) {
                user[i] = i;
            }
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            int m = Integer.parseInt(br.readLine());
            sb.append("Scenario "+tc+":").append('\n');
            for(int i = 0; i<m ; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                if(find(u) == find(v)){
                    sb.append(1).append('\n');
                }else{
                    sb.append(0).append('\n');
                }
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            user[fb] = fa;
        }
    }

    private static int find(int a) {
        if (user[a] == a) {
            return a;
        } else {
            return user[a] = find(user[a]); // 좌표 압축 연산
        }
    }
}
