
import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        //min heap
        PriorityQueue<Integer> largeHalf = new PriorityQueue<>();
        //max heap
        PriorityQueue<Integer> smallHalf = new PriorityQueue<>(Comparator.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //중간 값이 smallHalf(maxheap)의 루트로 존재하도록 조절
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(br.readLine());
            smallHalf.add(n);
            //작은 집합의 최댓값이 큰 집합의 최솟값 보다 크다면
            if(!smallHalf.isEmpty() && !largeHalf.isEmpty()
                    && smallHalf.peek() > largeHalf.peek()){
                largeHalf.add(smallHalf.poll());
            }
            //작은 집합의 사이즈가 큰 집합의 사이즈 보다 크다면
            if(smallHalf.size() > largeHalf.size()+1){
                largeHalf.add(smallHalf.poll());
            }
            // 큰 집합의 사이즈가 작은 집합의 사이즈 보다 크다면
            if(largeHalf.size() > smallHalf.size()){
                smallHalf.add(largeHalf.poll());
            }
            sb.append(smallHalf.peek()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
