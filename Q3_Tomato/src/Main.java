import java.io.*;
import java.util.*;

class Main {
    static int n; // 세로
    static int m; // 가로
    static int[][] tomatoBox; // 토마토
    static int[] x_around = { 1, -1, 0, 0 }; // 인접 토마토 접근 배열
    static int[] y_around = { 0, 0, 1, -1 }; // 인접 토마토 접근 배열

    static class Tomato {
        int x, y, day;

        public Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        tomatoBox = new int[m][n];

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(reader.readLine());

            for (int j = 0; j < n; j++) {
                tomatoBox[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        bfs();
    }

    static void bfs() {
        Queue<Tomato> ripe = new LinkedList<Tomato>(); // 익은 토마토 큐
        int day = 0;

        // 익은 토마토 큐 채우기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tomatoBox[i][j] == 1)
                    ripe.offer(new Tomato(i, j, 0));
            }
        }

        // START BFS
        while (!ripe.isEmpty()) {
            Tomato tomato = ripe.poll();
            day = tomato.day;

            for (int i = 0; i < 4; i++) {
                int nx = tomato.x + x_around[i];
                int ny = tomato.y + y_around[i];

                if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                    if (tomatoBox[nx][ny] == 0) {
                        tomatoBox[nx][ny] = 1;
                        ripe.add(new Tomato(nx, ny, day + 1));
                    }
                }
            }
        }

        if (checkTomatoStatus()) { // CHECK TOMATO STATUS...  is 1(true) or 0(fasle)
            System.out.println(day);
        } else { // empty tomato
            System.out.println(-1);
        }            
    }

    // continue bfs condition...
    static boolean checkTomatoStatus() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tomatoBox[i][j] == 0)
                    return false;
            }
        }

        return true;
    }
}