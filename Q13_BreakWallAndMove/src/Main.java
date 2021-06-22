import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, isBroke, count;
        public Point(int x, int y, int isBorke, int count) {
            this.x = x;
            this.y = y;
            this.isBroke = isBorke;
            this.count = count;
        }
    }

    static int n, m; // 세로, 가로
    static int[][] map;
    static boolean[][][] seen; // seen[][][0] = broke wall OR seen[][][1] = no break wall
    static int[] aroundX = { 1, -1, 0, 0 };
    static int[] aroundY = { 0, 0, -1, 1 };
    public static void main(String[] args) throws IOException {
        // INPUT start
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n + 1][m + 1];
        seen = new boolean[n + 1][m + 1][2];

        for (int _n = 1; _n <= n; _n++) {
            input = reader.readLine().split("");
            for (int _m = 1; _m <= m; _m++) {
                map[_n][_m] = Integer.parseInt(input[_m - 1]);
            }
        }
        // INPUT end
        bfs(); // BFS
    }

    static void bfs() {
        int x = 1;
        int y = 1;
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x, y, 0, 1));
        seen[x][y][0] = true;
        seen[x][y][1] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == n && point.y == m) { // GOAL!
                System.out.println(point.count); // OUTPUT
                return;
            }
            for (int i = 0; i < 4; i++) {
                int ax = point.x + aroundX[i];
                int ay = point.y + aroundY[i];
                int breakWall = point.isBroke;
                int count = point.count;

                if (ax <= 0 || ay <= 0 || ax > n || ay > m) { // CHECK OVER MAP
                    continue;
                }
                if (map[ax][ay] == 1) { // WALL! (can't pass square)
                    if (breakWall == 0 && !seen[ax][ay][1]) { // WALL is fine:)
                        seen[ax][ay][1] = true;
                        queue.add(new Point(ax, ay, 1, count + 1));
                    }
                } else { // can pass square
                    if (!seen[ax][ay][breakWall]) {
                        queue.add(new Point(ax, ay, breakWall, count + 1));
                        seen[ax][ay][breakWall] = true;
                    }
                }
            }
        }
        System.out.println(-1); // OUTPUT
    }
}