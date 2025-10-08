
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Country implements Comparable<Country> {
    int num, gold, silver, bronze;

    public Country(int num, int gold, int silver, int bronze) {
        this.num = num;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    @Override
    public int compareTo(Country o) {
        return this.gold == o.gold ? this.silver == o.silver ? o.bronze - this.bronze : o.silver - this.silver : o.gold - this.gold;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Country[] countries = new Country[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            countries[i] = new Country(num, gold, silver, bronze);
        }
        Arrays.sort(countries);
        for (int i = 0; i < countries.length; i++) {
            Country country = countries[i];
            if (country.num == K) {
                System.out.println(i+1);
                return;
            }
        }
    }
}
