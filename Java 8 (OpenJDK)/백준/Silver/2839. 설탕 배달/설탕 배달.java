
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt = 0;

		if (N % 5 == 0) {
			System.out.println(N / 5);
		} else {
			while (N != 0) {
				N -= 3;
				cnt++;
				if (N % 5 == 0) {
					cnt += N / 5;
					System.out.println(cnt);
					break;
				} else if (N == 1 || N == 2) {
					System.out.println(-1);
					break;
				} else if (N == 0) {
					System.out.println(cnt);
					break;
				}
			}
		}
	}
}
