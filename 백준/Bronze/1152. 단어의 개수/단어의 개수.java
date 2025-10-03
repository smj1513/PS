import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim(); // 1. 먼저 trim()까지 수행

        // 2. trim()된 문자열이 비어있는지 확인
        if (input.isEmpty()) {
            System.out.println(0);
        } else {
            // 3. 비어있지 않을 때만 split()으로 단어 개수 계산
            System.out.println(input.split(" ").length);
        }
    }
}