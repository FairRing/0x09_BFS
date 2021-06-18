import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int testCase; // 테스트갯수
    static int chessSize; // 체스판 크기
    static int[][] chess; // 체스판
    static int[] around_x = { -1, 1, 2, 2, 1, -1, -2, -2 }; // 나이트 이동 가능 X값
    static int[] around_y = { 2, 2, 1, -1, -2, -2, 1, -1 }; // 나이트 이동 가능 Y값
    static int result; // 결과

    public static void main(String[] args) throws IOException {
        // SECTION INPUT...
        Scanner sc = new Scanner(System.in);
        Queue<Point> queue = new LinkedList<>();

        testCase = sc.nextInt();

        for (int i = 0; i < testCase; i++) {
            chessSize = sc.nextInt();
            chess = new int[chessSize][chessSize];

            Point pointStart = new Point(sc.nextInt(), sc.nextInt());
            Point pointEnd = new Point(sc.nextInt(), sc.nextInt());
            chess[pointStart.x][pointStart.y] = 1;

            queue.add(new Point(pointStart.x, pointStart.y));

            // SECTION BFS...
            GOTO: while (true) {
                int nowQueueSize = queue.size();

                if (nowQueueSize == 0) break;
                int n = 0;

                while (n++ < nowQueueSize) {
                    Point beforePoint = queue.poll();

                    if (beforePoint.x == pointEnd.x && beforePoint.y == pointEnd.y) break GOTO; // NOTE RESTART WHILE(){...}

                    for (int j = 0; j < 8; j++) {
                        int ax = beforePoint.x + around_x[j];
                        int ay = beforePoint.y + around_y[j];

                        if (ax < 0 || ax >= chessSize || ay < 0 || ay >= chessSize || chess[ax][ay] == 1) continue;

                        chess[ax][ay] = 1; 
                        queue.add(new Point(ax, ay)); 
                    }
                }
                result++;
            }
            // SECTION OUTPUT...
            System.out.println(result);
            result = 0;
			queue.clear();
        }

        sc.close();
    }
}
