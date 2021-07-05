import java.io.*;
import java.util.*;

public class Main {
    private static int N, K, SIZE = 100001;
    private static int[] visited = new int[SIZE], parent = new int[SIZE];

    public static void main(String[] args) throws IOException {
        // INPUT :: start, goal location
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        bfs(N, K); // BFS
        
        // OUTPUT :: second
        System.out.println(visited[K]-1);
        // OUTPUT :: shortest path
        Stack<Integer> stack = new Stack<Integer>();
        int history = K;
        while (history != N) {
            stack.push(history);
            history = parent[history];
        }
        stack.push(history);
        while (!stack.isEmpty()) System.out.print(stack.pop()+" ");

        reader.close();
    }

    private static void bfs(int start, int goal) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now+1 <= 100000 && visited[now+1] == 0) {
                visited[now+1] = visited[now]+1;
                parent[now+1] = now;
                queue.add(now+1);
            }
            if (now-1 >= 0 && visited[now-1] == 0) {
                visited[now-1] = visited[now]+1;
                parent[now-1] = now;
                queue.add(now-1);
            }
            if (now*2 <= 100000 && visited[now*2] == 0) {
                visited[now*2] = visited[now]+1;
                parent[now*2] = now;
                queue.add(now*2);
            }
            if (visited[goal] != 0) return;
        }
    }
}