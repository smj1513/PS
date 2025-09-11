import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char ch = br.readLine().charAt(0);
		
		if(ch == 'N' || ch == 'n') {
			System.out.println("Naver D2");
		} else {
			System.out.println("Naver Whale");
		}
		
	}

}
