
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static char[][] map;
    static int h, w;
    static int[] dx = {-1, 1, 0, 0};//상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static int x = 0, y = 0, dir; // 0 1 2 3 -> 상하좌우
    static Map<Character, Integer> directionMap = new HashMap<>();
    static Map<Integer, Character> reverseDirMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        directionMap.put('^', 0);
        directionMap.put('v', 1);
        directionMap.put('<', 2);
        directionMap.put('>', 3);
        reverseDirMap.put(0, '^');
        reverseDirMap.put(1, 'v');
        reverseDirMap.put(2, '<');
        reverseDirMap.put(3, '>');
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (directionMap.containsKey(map[i][j])) {
                        x = i;
                        y = j;
                        dir = directionMap.get(map[i][j]);
                    }
                }
            }
            Integer.parseInt(br.readLine());
            for (char command : br.readLine().toCharArray()) {
                play(command);
            }
            System.out.printf("#%d ", tc);
            for (int i = 0; i < w; i++) {
                System.out.print(map[0][i]);
            }
            System.out.println();
            for (int i = 1; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void play(char command) {
        int drx, dry;
        switch (command) {
            case 'S':
                drx = x + dx[dir];
                dry = y + dy[dir];
                while (valid(drx, dry)) {
                    if (map[drx][dry] == '#') {
                        break;
                    }
                    if (map[drx][dry] == '*') {
                        map[drx][dry] = '.';
                        break;
                    }
                    drx = drx + dx[dir];
                    dry = dry + dy[dir];
                }
                break;
            case 'U':
                dir = 0;
                move(dir);
                break;
            case 'D':
                dir = 1;
                move(dir);
                break;
            case 'L':
                dir = 2;
                move(dir);
                break;
            case 'R':
                dir = 3;
                move(dir);
                break;
        }
    }

    public static void move(int direction) {
        int drx = x + dx[direction];
        int dry = y + dy[direction];
        if (valid(drx, dry) && map[drx][dry] == '.') {
            map[x][y] = '.';
            x = drx;
            y = dry;
            map[drx][dry] = reverseDirMap.get(dir);
        } else {
            map[x][y] = reverseDirMap.get(dir);
        }
    }

    public static boolean valid(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}
