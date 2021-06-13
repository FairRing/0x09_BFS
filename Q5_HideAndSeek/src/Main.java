import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int n;
    static int k;
    static int[] array = new int[100001]; // 숨바꼭질 범위 시간(초) 저장

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        if (n == k) { // before bfs check
            System.out.println(0);
        } else {
            bfs(n);
        }

        sc.close();
    }

    static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(num);
        array[num] = 1;


        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) {
                    next = temp + 1;
                } else if (i == 1) {
                    next = temp - 1;
                } else {
                    next = temp * 2;
                }

                if (next == k) {
                    System.out.println(array[temp]);
                    return;
                }

                // 숨바꼭질 범위 안, 처음 방문 위치
                if (next >= 0 && next < array.length && array[next] == 0) {
                    queue.add(next);
                    array[next] = array[temp] + 1;
                }
            }
        }
    }
}