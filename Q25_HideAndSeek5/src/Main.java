import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 500000, ODD = 1, EVEN = 0;
    static int S, B;
    static Queue<Integer> queue;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input[] = reader.readLine().split(" ");
        S = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);

        queue = new LinkedList<>();
        visited = new boolean[2][MAX + 1];

        if (S == B) {
            System.out.println(0);
            return;
        }
        queue.offer(S);
        System.out.println(bfs());
    }

    private static int bfs() {
        int essenceType = 0, second = 0, tempB = B;

        while (!queue.isEmpty()) {
            int size = queue.size();

            essenceType = second % 2 == 0 ? EVEN : ODD;

            for (int i = 0; i < size; ++i) {
                int tempS = queue.poll();

                if (tempS == tempB) return second; // GOAL 1

                if (tempS * 2 <= MAX) {
                    if (!visited[essenceType][tempS * 2]) {
                        visited[essenceType][tempS * 2] = true;
                        queue.offer(tempS * 2);
                    }
                }
                if (tempS + 1 <= MAX) {
                    if (!visited[essenceType][tempS + 1]) {
                        visited[essenceType][tempS + 1] = true;
                        queue.offer(tempS + 1);
                    }
                }
                if (tempS - 1 >= 0) {
                    if (!visited[essenceType][tempS - 1]) {
                        visited[essenceType][tempS - 1] = true;
                        queue.offer(tempS - 1);
                    }
                }
            }

            tempB += ++second;
            if (tempB > MAX) return -1; // GOAL :: 찾는 위치가 500,000을 넘는 경우에는 -1
            if (visited[essenceType][tempB]) return second; // GOAL 2
        }

        return -1;
    }
}