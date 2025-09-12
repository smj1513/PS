
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Person implements Comparable<Person> {
    static final int M = 1, W = 2, D = 3, C = 4;
    int x, y, status, arrivalTime, downCnt;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;

    }

    void init() {
        arrivalTime = downCnt = 0;
        status = M;
    }

    @Override
    public int compareTo(Person o) {
        return this.arrivalTime - o.arrivalTime;
    }
}

//이 방법으로는 안됨..
//가까운 계단을 찾아가는 그리디적인 방법으로는 해당 계단의 이동시간과 3명 대기 제한으로 인한 병목 시간 증가를 계산하기 어려움.
public class Solution {
    static int N, min, cnt; //맵의 크기, 최소이동시간
    static int[][] sList; // 계단 리스트(r, c, height)
    static ArrayList<Person> pList; // 사람 리스트
    static int[] selected; //사람마다 어떤 계단 배정되었는지 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            pList = new ArrayList<>();
            sList = new int[2][];
            min = Integer.MAX_VALUE;
            for (int i = 0, k = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value == 1) pList.add(new Person(i, j));
                    else if (value > 1) sList[k++] = new int[]{i, j, value};
                }
            }
            cnt = pList.size();
            selected = new int[cnt];

            divied(0);
            System.out.println("#" + tc + " " + min);
        }

    }

    private static void divied(int index) {// index에 해당하는 사람의 계단 배정
        if (index == cnt) {
            makeList();
            return;
        }
        selected[index] = 0;
        divied(index + 1);
        selected[index] = 1;
        divied(index + 1);
    }

    private static void makeList() {// 계단 배정에 따른 사람들 리스트에 배치
        ArrayList<Person>[] list = new ArrayList[]{new ArrayList<Person>(), new ArrayList<Person>()};
        for (int i = 0; i < cnt; i++) {
            Person p = pList.get(i);
            p.init();
            int no = selected[i];

            p.arrivalTime = Math.abs(p.x - sList[no][0]) + Math.abs(p.y - sList[no][1]);
            list[no].add(p);
        }

        int timeA = processDown(list[0], sList[0][2]);
        int timeB = processDown(list[1], sList[1][2]);
        int res = Math.max(timeA, timeB);
        min = Math.min(min, res);
    }

    private static int processDown(ArrayList<Person> people, int height) {
        if (people.isEmpty()) return 0;
        Collections.sort(people);
        int time = people.get(0).arrivalTime;
        int size = people.size();
        int ingCnt = 0, cCnt = 0;
        while (true) {
            for (int i = 0; i < size; i++) {
                Person p = people.get(i);
                if (p.status == Person.C) continue;
                if (p.arrivalTime == time) {
                    p.status = Person.W;
                } else if (p.status == Person.W && ingCnt < 3) {
                    p.status = Person.D;
                    p.downCnt = 1;
                    ++ingCnt;
                } else if (p.status == Person.D) {
                    if (p.downCnt < height) {
                        p.downCnt++;
                    } else {
                        p.status = Person.C;
                        --ingCnt;
                        ++cCnt;
                    }
                }
            }

            if (cCnt == size) break;
            time++;
        }

        return time;
    }
}
