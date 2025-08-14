
import java.util.Scanner;

public class Main {

    static int[][] spaces;
    static int green, white;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        spaces = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                spaces[i][j] = sc.nextInt();
            }
        }
        green = white = 0;
        makeSpace(0, 0, N);
        System.out.println(white);
        System.out.println(green);
    }

    //좌상단 좌표, 사이즈
    static void makeSpace(int r, int c, int size) {
        //step 1. 주어진 영역이 모두 같은 색상으로 이루어졌는지 체크
        int sum = 0;
        for (int i = r, rEnd = r + size; i < rEnd; i++) {
            for (int j = c, cEnd = c + size; j < cEnd; j++) {
                sum += spaces[i][j];
            }
        }

        //기저 : 모두 같은 색상이거나 크기가 1일 때
        //step 2. 같은 색으로 이루어져있다면 해당 색에 카운트 증가 후 리턴
        if (sum == size * size) { // 모두 초록색이면
            ++green;
            //step 3. 같은 색으로 이루어져있지 않다면, 4분할 후 각 공간에 대해 동일한 처리
        } else if (sum == 0) { //모두 하얀색이면
            ++white;
        } else {//섞여있으면
            int newSize = size / 2;
            makeSpace(r, c, newSize);//영역 1
            makeSpace(r + newSize, c, newSize);
            makeSpace(r, c + newSize, newSize);
            makeSpace(r + newSize, c + newSize, newSize);
        }


    }
}
