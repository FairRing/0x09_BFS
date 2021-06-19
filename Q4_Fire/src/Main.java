import java.io.*;
import java.util.*;

    /* NOTE * * * * * * * * * * * * * * * * * * * * * * * * * * *
       * 1st input line info...  (1 <= R,C <= 1000)
       R : 미로 행의 개수(세로)
       C : 열의 개수(가로)

       * other input lines info...
       # : 벽
       . : 지나갈 수 있는 공간
       J : 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
       F : 불이 난 공간
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

// NOTE :: each coordinate info class...
class Point {
    int x, y, value, count;
    public Point(int x, int y, int value, int count) {
        this.x = x;
        this.y = y;
        this.value = value;   // CASE : J = 1 / F = 0
        this.count = count;
    }
}

public class Main {
    static int R, C;
    static Point J;
    static char[][] maze;
    static boolean[][] seen;
    static int[] aroundX = {1,-1,0,0};
    static int[] aroundY = {0,0,-1,1};
    static String FAIL = "IMPOSSIBLE";

    // NOTE :: check location is in map'scale...
    static boolean isInRange(int x, int y) {

        if ((x >= 0 && x < R) && (y >= 0 && y < C)) return true;

        return false;
    }
   // NOTE :: check location get included map's border line locations...
    static boolean isBorderLine(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int ax = x + aroundX[i];
            int ay = y + aroundY[i];

            if (!isInRange(ax, ay)) return true;
        }
        return false;
    }
    // NOTE :: fire는 번짐 상하좌우
    static void bfs(Queue<Point> fire) {
        int x, y, count;

        fire.add(J); // NOTE :: 입력된 불(저장) > J 위치 저장
        seen[J.x][J.y] = true;

        while (!fire.isEmpty()) {
            Point point = fire.poll();
            x = point.x;
            y = point.y;
            count = point.count;

            // 종료 조건
            if (isBorderLine(x, y) && point.value == 0) {
                System.out.println(count + 1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int ax = x + aroundX[i];
                int ay = y + aroundY[i];

                if (!isInRange(ax, ay) || maze[ax][ay] == '#' || maze[ax][ay] == 'F') continue;
                
                // CONDITION OF J
                if (point.value == 0 && !seen[ax][ay]) { 
                    fire.add(new Point(ax, ay, point.value, count + 1));
                    seen[ax][ay] = true;
                }
                // CONDITION OF F
                if (point.value == 1) {
                    maze[ax][ay] = 'F';
                    fire.add(new Point(ax, ay, point.value, count + 1));
                }
            }
        }
        System.out.println(FAIL);
    }
    public static void main(String[] args) throws Exception {
        // SECTION :: start input...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tempReader = reader.readLine().split(" ");
        R = Integer.parseInt(tempReader[0]);
        C = Integer.parseInt(tempReader[1]);
        maze = new char[R][C];
        seen = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            tempReader = reader.readLine().split("");
            for (int j = 0; j < C; j++) {
                maze[i][j] = tempReader[j].charAt(0);
            }
        }
        // SECTION :: end input.
        // SECTION :: start Bfs...
        /* NOTE
           > J 값을 따로 관리(J는 하나임) 
           >> 기존 J 값을 .로 변경
           >>> F는 큐로 관리
           >>>> J의 위치에 따른 결과 도출 차이
                1. J가 테두리면 바로 탈출가능함 = 1
                2. J가 테두리가 아님 = bfs적용, 화상당할수있음(F좌표 큐에 저장)
        */
        Queue<Point> fire = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (maze[i][j] == 'J') {
                    if (isBorderLine(i, j)) {
                        System.out.println(1); // SECTION ::  output...
                        return; // EXIT!
                    }
                    J = new Point(i, j, 0, 0);
                    maze[i][j] = '.';
                }
                 
                if (maze[i][j] == 'F') fire.add(new Point(i, j, 1, 0));
            }
        }
        bfs(fire); // NOTE :: all fire added from input maze's values
        // SECTION :: end Bfs.
    }
}
