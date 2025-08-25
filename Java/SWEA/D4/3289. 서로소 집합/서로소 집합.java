
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] parents = new int[N + 1];
            makeSet(parents);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (command == 0) {
                    union(parents, a, b);
                } else if (command == 1) {
                    int fa = find(parents, a);
                    int fb = find(parents, b);
                    if (fa == fb) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }
            System.out.println("#" + tc + " " + sb);
        }
    }

    public static void makeSet(int[] parents) {
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    public static void union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);
        if (a != b) {
            parents[b] = a;
        }
    }

    public static int find(int[] parents, int a) {
        if (parents[a] == a) {
            return a;
        } else {
            return parents[a] = find(parents, parents[a]);
        }
    }
}
