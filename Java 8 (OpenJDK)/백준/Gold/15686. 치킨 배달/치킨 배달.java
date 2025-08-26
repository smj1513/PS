
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distance(Point other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}

public class Main {
    static int N, M;
    static List<Point> house = new ArrayList<>(), chicken = new ArrayList<>();
    static int minDistance = Integer.MAX_VALUE;
    static Point[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        temp = new Point[M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    house.add(new Point(i + 1, j + 1));
                } else if (a == 2) {
                    chicken.add(new Point(i + 1, j + 1));
                }
            }
        }
        combination(0, 0);
        System.out.println(minDistance);
    }

    public static void combination(int start, int depth) {
        if (depth == M) { //폐업 시키지 않을 치킨 수
            int distance = 0;
            for (Point home : house) {
                int chickenDistance = Integer.MAX_VALUE;
                for (Point ch : temp) {
                    chickenDistance = Math.min(home.distance(ch), chickenDistance);
                }
                distance += chickenDistance;
            }
            minDistance = Math.min(distance, minDistance);
            return;
        } else {
            for (int i = start; i < chicken.size(); i++) {
                temp[depth] = chicken.get(i);
                combination(i + 1, depth + 1);
            }
        }
    }
}
