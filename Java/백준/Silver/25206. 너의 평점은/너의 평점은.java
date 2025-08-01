import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Map<String, Double> gradeMap = Map.of(
			"A+", 4.5,
			"A0", 4.0,
			"B+", 3.5,
			"B0", 3.0,
			"C+",2.5,
			"C0", 2.0,
			"D+",1.5,
			"D0",1.0,
			"F",0.0
	);
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Double> grade = new HashMap<>();
		double dd = 0.0;
		for (int i = 0; i < 20; i++) {
			String name = sc.next();
			double g = sc.nextDouble();
			String gra = sc.next();
			sc.nextLine();
			if (!gra.equals("P")) {
				Double gg = gradeMap.get(gra);
				grade.put(name, g*gg);
				dd+=g;
			}
		}
		double avg = 0.0;
		for (Double d : grade.values()) {
			avg += d;
		}
		System.out.println(dd != 0.0 ?avg / dd : 0.0);
	}
}
