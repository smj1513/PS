import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static boolean[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new boolean[21];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken();
			
			if(command.equals("all")) {
				Arrays.fill(arr, true);
				continue;
			}
			
			if(command.equals("empty")) {
				Arrays.fill(arr, false);
				continue;
			}
			
			int num = Integer.parseInt(st.nextToken());
			
			switch(command) {
			case "add":
				arr[num] = true;
				break;
			case "remove":
				arr[num] = false;
				break;
			case "check":
				if(arr[num]) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
				break;
			case "toggle":
				if(arr[num]) {
					arr[num] = false;
				} else {
					arr[num] = true;
				}
				break;
			}
		}
		
		System.out.println(sb);
		
	}

}
