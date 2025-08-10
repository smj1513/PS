
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Ingredient {
	int s;
	int b;

	public Ingredient(int s, int b) {
		this.s = s;
		this.b = b;
	}
}

public class Main {
	static Ingredient[] ingredients;
	static boolean[] isSelected;
	static int n;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ingredients = new Ingredient[n];
		isSelected = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ingredients[i] = new Ingredient(s, b);
		}
		subset(0);
		System.out.println(result);
	}

	static void subset(int depth) {
		if (depth == n) {
			boolean isAnySelected = false;
			int s = 1;
			int b = 0;
			for (int i = 0; i < ingredients.length; i++) {
				if (isSelected[i]) {
					isAnySelected = true;
					s *= ingredients[i].s;
					b += ingredients[i].b;
				}
			}
			if (isAnySelected) {
				result = Math.min(result, Math.abs(s - b));
			}
			return;
		} else {
			isSelected[depth] = true;
			subset(depth + 1);
			isSelected[depth] = false;
			subset(depth + 1);
		}
	}


}
