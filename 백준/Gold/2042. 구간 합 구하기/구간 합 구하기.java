
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class FenwickTree {
    long[] tree;

    public FenwickTree(int size) {
        tree = new long[size + 1];
    }

    public void update(int idx, long diff) {
        for (int i = idx; i < tree.length; i += (i & -i)) {
            tree[i] += diff;
        }
    }

    public long sum(int idx) {
        long result = 0;
        for (int i = idx; i > 0; i -= (i & -i)) {
            result += tree[i];
        }
        return result;
    }

    public long query(int start, int end) {
        return sum(end) - sum(start - 1);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N + 1];
        FenwickTree indexTree = new FenwickTree(N);
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
            indexTree.update(i, arr[i]);
        }
        for (int i = 1; i <= M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                //데이터 변경
                indexTree.update(b, c - arr[b]);
                arr[b] = c;
            } else if (a == 2) {
                System.out.println(indexTree.query(b, (int)c));
            }
        }
    }
}
