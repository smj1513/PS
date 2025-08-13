
import java.util.Scanner;

public class Main {
	static int[] arr = {1, 2, 3};
	static int[] temp;
	static int N;
	static boolean isFind;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		temp = new int[N];
		backTracking(0);
	}

	public static void backTracking(int depth) {
		if(isFind){
			return;
		}
		if (depth == N) {
			for(int num : temp) System.out.print(num);
			isFind = true;
			return;
		} else {
			for (int i = 0; i < arr.length; i++) {
				temp[depth] = arr[i];
				if (isGood(depth)) {
					backTracking(depth + 1);
				}
			}
		}
	}

	public static boolean isGood(int depth) {
		for (int len = 1; len <= (depth + 1) / 2; len++) {
			boolean same = true;
			for (int i = 0; i < len; i++) {
				if (temp[depth - i] != temp[depth - len - i]) {
					same = false;
					break;
				}
			}
			if (same) return false;
		}
		return true;
	}

}
