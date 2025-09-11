import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		if(b == 0) {
			System.out.println(0);
			return;
		} else if(a == 0) {
			System.out.println(1);
			return;
		} else if(a < b){
			System.out.println(a + 1);
			return;
		} else if(a > b) {
			System.out.println(b);
			return;
		} else {
			System.out.println(b);
		}
		
	}

}
