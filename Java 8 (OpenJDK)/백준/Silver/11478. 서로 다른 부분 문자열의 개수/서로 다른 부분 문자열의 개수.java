import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Set<String> strings = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j <= input.length(); j++) {
                String substring = input.substring(i, j);
                strings.add(substring);
            }
        }
        System.out.println(strings.size()-1);
    }
}