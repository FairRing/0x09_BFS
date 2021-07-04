import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int N, M, countLights = 0;
    static ArrayList<Point>[][] room;
    static boolean[][] isVisited, isTurnOn, isNove;
    static int[] aroundX = { 1, -1, 0, 0 }, aroundY = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = 1 + Integer.parseInt(input[0]); // statr point = 1,1
        M = Integer.parseInt(input[1]);
        room = new ArrayList[N][N]; // type safety...
        isVisited = new boolean[N][N];
        isTurnOn = new boolean[N][N];
        isNove = new boolean[N][N];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                room[i][j] = new ArrayList<Point>();
            }
        }
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            room[Integer.parseInt(input[0])][Integer.parseInt(input[1])].add(new Point(Integer.parseInt(input[2]), Integer.parseInt(input[3])));
        }
        bfs();
        System.out.println(countLights);
    } // end scope :: main method.

    private static void bfs() {
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(1, 1));
        isTurnOn[1][1] = true;
        isVisited[1][1] = true;
        countLights++;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            // SECTION :: turn on lights...
            for (int i = 0; i < room[now.x][now.y].size(); i++) {
                Point tempTurnOn = room[now.x][now.y].get(i);

                if (isTurnOn[tempTurnOn.x][tempTurnOn.y]) continue;
                isTurnOn[tempTurnOn.x][tempTurnOn.y] = true;
                countLights++;
            }
            // SECTION :: can Move....
            for (int i = 0; i < 4; i++) {
                int aX = now.x + aroundX[i], aY = now.y + aroundY[i];

                if (aX < 0 || aX > N - 1 || aY < 0 || aY > N - 1) continue;
                isNove[aX][aY] = true;
            }
            // SECTION :: reCheck...
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (isTurnOn[i][j] && isNove[i][j] && !isVisited[i][j]) {
                        isVisited[i][j] = true;
                        queue.add(new Point(i, j));
                    }
                }
            }
        } // end scope :: loop while.
    } // end scope :: bfs method.
} // end scope :: main class.