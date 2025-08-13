
import java.util.Scanner;

//N-Queen
public class Solution {
    static int N, answer = 0;
    static boolean[] col, slash, bSlash;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for(int tc = 1; tc <= T; tc++){
        N = sc.nextInt();
        sc.nextLine();
        col = new boolean[N + 1];
        slash = new boolean[2 * N + 1]; // 합이 일정한 성질(2~2N)
        bSlash = new boolean[2 * N]; //차가 일정한 성질(-N-1~N-1) : 음수 보정 + N
        setQueen(1);
        System.out.println("#"+tc+" "+answer);
         answer = 0;
        }
    }

    static void setQueen(int row) { // row : 퀸을 놓아야하는 현재 행
        if(row > N){
            answer++;
            return;
        }
        //유도 파트
        for (int c = 1; c <= N; c++) {
            if(col[c] || slash[row+c] || bSlash[row-c+N]) continue; //가지 치기
            col[c] = slash[row + c] = bSlash[row - c + N] = true;
            //다음 퀸 놓기
            setQueen(row + 1);
            col[c] = slash[row + c] = bSlash[row - c + N] = false;
        }
    }
}
