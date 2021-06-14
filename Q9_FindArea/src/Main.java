import java.io.*;
import java.util.*;

/* NOTE * * * * * * * * * * * * * * * * * * * * 
- 가로 : M
- 세로 : N
- K : 그려질 직사각형(겹처서 그릴수 있음)
- 1 ≤ M, N, K ≤ 100
- (세로 N , 가로 M)
- (ex) 5 7 3 == (0,0) ~ (7,5), 3개 직사각형
       ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
       ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
       ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
       ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
       ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ ㅁ
 * * * * * * * * * * * * * * * * * * * * * * */

public class Main {

    static int m, n, k;
    static int[][] areaTotal;
    static boolean[][] visited;
    static int[] aroundX = { 0, 1, 0, -1 };
    static int[] aroundY = { 1, 0, -1, 0 };

    // NOTE BFS
    private static int bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] { x, y });
        visited[x][y] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int beforeX = temp[0];
            int beforeY = temp[1];

            for (int i = 0; i < 4; i++) {
                int nextX = beforeX + aroundX[i];
                int nextY = beforeY + aroundY[i];

                if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n) {
                    if (!visited[nextX][nextY] && areaTotal[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                        queue.offer(new int[] { nextX, nextY });
                        count = count + 1;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        // SECTION START SET FROM INPUT VALUES...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(token.nextToken());
        n = Integer.parseInt(token.nextToken());
        k = Integer.parseInt(token.nextToken());

        areaTotal = new int[m][n];
        visited = new boolean[m][n];
        int count = 0;
        Vector<Integer> result = new Vector<>();

        for (int k_ = 0; k_ < k; k_++) {
            token = new StringTokenizer(reader.readLine());
            int leftTopX = Integer.parseInt(token.nextToken()); // x 왼쪽위
            int leftTopY = Integer.parseInt(token.nextToken()); // y 왼쪽위
            int rightBottomX = Integer.parseInt(token.nextToken()); // y 오른쪽아래
            int rightBottomY = Integer.parseInt(token.nextToken()); // y 오른쪽아래
            for (int y = leftTopY; y < rightBottomY; y++) {
                for (int x = leftTopX; x < rightBottomX; x++) {
                    areaTotal[y][x] = 1;
                }
            }
        }
        // SECTION END SET FROM INPUT VALUES...
        // SECTION START BFS
        for (int m_ = 0; m_ < m; m_++) {
            for (int n_ = 0; n_ < n; n_++) {
                if (!visited[m_][n_] && areaTotal[m_][n_] == 0) {
                    int data = bfs(m_, n_);
                    result.add(data);
                    count++;
                }
            }
        }
        Collections.sort(result);
        // SECTION END BFS
        // SECTION OUTPUT...
        System.out.println(count);
        for (int i = 0; i < result.size(); i++) System.out.print(result.get(i) + " ");
    }
}
