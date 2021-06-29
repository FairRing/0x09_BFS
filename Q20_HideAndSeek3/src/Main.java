import java.util.*;

public class Main {
    static int N, K, second = Integer.MAX_VALUE;
    static int[] map = new int[100001];
    static boolean[] visited = new boolean[100001];
    static Queue<Integer> queue;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();

        queue = new LinkedList<Integer>();
        visited[N] = true;
        queue.offer(N);

        bfs();

        System.out.println(second);
        scanner.close();
    }

    public static void bfs() {
        
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // GOAL
            if (current == K) {
                second = Math.min(second, map[current]);
                break;
            }

            // walk -1 :: take +1 second
            if (0 <= current - 1 && !visited[current - 1]) {
                queue.offer(current - 1);
                visited[current - 1] = true;
                map[current - 1] = map[current] + 1;
            }

            // walk * 2 :: take 0 second
            if (current * 2 <= 100000 && !visited[current * 2]) {
                queue.offer(current * 2);
                visited[current * 2] = true;
                map[current * 2] = map[current];
            }

            // walk +1 :: take +1 second
            if (current + 1 <= 100000 && !visited[current + 1]) {
                queue.offer(current + 1);
                visited[current + 1] = true;
                map[current + 1] = map[current] + 1;
            }
        }
    }
}