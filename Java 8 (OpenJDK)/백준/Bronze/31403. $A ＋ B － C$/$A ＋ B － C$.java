import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String A = sc.nextLine();
		String B = sc.nextLine();
		String C = sc.nextLine();
		System.out.println(Integer.parseInt(A) + Integer.parseInt(B) - Integer.parseInt(C));
		System.out.println(Integer.parseInt(A+B)-Integer.parseInt(C));
	}
}
