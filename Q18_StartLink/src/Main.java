import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;

    public static void main(String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputValue = reader.readLine().split(" ");

        F = Integer.parseInt(inputValue[0]); // 층 : 빌딩 꼭대기 층
        S = Integer.parseInt(inputValue[1]); // 층 : 강호 ( START )
        G = Integer.parseInt(inputValue[2]); // 층 : 스타트링크( GOAL )
        U = Integer.parseInt(inputValue[3]); // 버튼 : 위로
        D = Integer.parseInt(inputValue[4]); // 버튼 : 아래로
        int[] B = new int[F + 1];
        System.out.println(BFS(F, S, G, U, D, B));

    }

    public static String BFS(int floor, int start, int goal, int up, int down, int[] B) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        B[start] = 1;

        while (!queue.isEmpty()) {
            int element = queue.poll();
            if (element == goal) {
                return String.valueOf(B[element] - 1);
            }
            if (element + up <= floor) {
                if (B[element + up] == 0) {
                    B[element + up] = B[element] + 1;
                    queue.offer(element + up);
                }

            }
            if (element - down > 0) {
                if (B[element - down] == 0) {
                    B[element - down] = B[element] + 1;
                    queue.offer(element - down);
                }
            }

        }
        return "use the stairs";
    }
}
