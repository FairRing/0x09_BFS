import java.io.*;
import java.util.*;
/* NOTE :: element'size type list
  - . : 빈 공간   <-- 이동가능
  - # : 벽        <-- 이동불가
  - @ : 시작 위치 <-- 이동가능
  - * : 불        <-- 이동불가
 */

public class Main {
    static int n, m, result;
    static char[][] map;
    static Queue<Location> fire;
    static int[] aroundX = { -1, 1, 0, 0 }, aroundY = { 0, 0, -1, 1 };

    public static class Location {
        int x, y, sec;
        public Location(int x, int y, int sec) {
            this.x = x;
            this.y = y;
            this.sec = sec;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String arrayResult[] = new String[t];

        for (int i = 0; i < t; i++) {
            String[] input = br.readLine().split(" ");
            m = Integer.parseInt(input[0]);
            n = Integer.parseInt(input[1]);
            map = new char[n][m];
            fire = new LinkedList<>();
            Location man = null;
            result = 0;

            for (int j = 0; j < n; j++) {
                String str = br.readLine();
                for (int j2 = 0; j2 < m; j2++) {

                    map[j][j2] = str.charAt(j2);

                    if (map[j][j2] == '*') {
                        fire.add(new Location(j, j2, 0));
                    }
                    if (map[j][j2] == '@') {
                        man = new Location(j, j2, 0);
                        map[j][j2] = '.';
                    }
                }
            }

            bfs(man); // BFS
            if (result == 0) arrayResult[i] = "IMPOSSIBLE";
            else arrayResult[i] = Integer.toString(result);
        }

        for (String string : arrayResult) System.out.println(string); // OUTPUT
    }

    public static void bfs(Location man) {
        Queue<Location> queue = new LinkedList<Location>();
        boolean[][] visited = new boolean[n][m];
        queue.add(man);
        visited[man.x][man.y] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            spreadFire();

            for (int i = 0; i < size; i++) {
                Location manLocation = queue.poll();

                if (manLocation.x <= 0 || manLocation.x >= n - 1 || manLocation.y <= 0 || manLocation.y >= m - 1) {
                    result = manLocation.sec + 1;
                    return;
                }

                for (int j = 0; j < 4; j++) {
                    int X = manLocation.x + aroundX[j];
                    int Y = manLocation.y + aroundY[j];

                    if (map[X][Y] == '*' || map[X][Y] == '#' || visited[X][Y]) continue;

                    visited[X][Y] = true;
                    queue.add(new Location(X, Y, manLocation.sec + 1));
                }
            }
        }
    }

    public static void spreadFire() {
        int size = fire.size();

        for (int i = 0; i < size; i++) {
            Location fireLocation = fire.poll();

            for (int j = 0; j < 4; j++) {
                int X = fireLocation.x + aroundX[j];
                int Y = fireLocation.y + aroundY[j];

                if (X < 0 || X >= n || Y < 0 || Y >= m || map[X][Y] == '*' || map[X][Y] == '#') continue;

                map[X][Y] = '*';
                fire.add(new Location(X, Y, 0));
            }
        }
    }
}