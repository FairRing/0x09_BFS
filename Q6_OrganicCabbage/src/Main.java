import java.io.*;
import java.util.*;

// NOTE CLASS LOCATION
class Location {
    int x, y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {

    static int n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static int[] around_x = { -1, 1, 0, 0 };
    static int[] around_y = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
    
        // SECTION :: INPUT
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int t_ = 1; t_ <= t; t_++) {
            String[] temp = br.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            m = Integer.parseInt(temp[1]);
            k = Integer.parseInt(temp[2]);

            map = new int[n][m];
            for (int k_ = 0; k_ < k; k_++) {
                temp = br.readLine().split(" ");
                int x = Integer.parseInt(temp[0]);
                int y = Integer.parseInt(temp[1]);
                map[x][y] = 1;
            }

            // SECTION :: BFS
            int count = 0;
            visited = new boolean[n][m];
            for (int n_ = 0; n_ < n; n_++) {
                for (int m_ = 0; m_ < m; m_++) {
                    if (map[n_][m_] != 0 && !visited[n_][m_]) {
                        bfs(n_, m_);
                        count++;
                    }
                }
            }
            // SECTION :: END
            System.out.println(count);
        }
        br.close();
    }

    // NOTE :: BFS METHOD
    public static void bfs(int startX, int startY) {
        Queue<Location> queue = new LinkedList<>();

        visited[startX][startY] = true;
        queue.offer(new Location(startX, startY));

        while (!queue.isEmpty()) {
            Location location = queue.poll();
            int x = location.x;
            int y = location.y;

            for (int i = 0; i < 4; i++) {
                int seeX = x + around_x[i];
                int seeY = y + around_y[i];

                if (checkLocation(seeX, seeY) && !visited[seeX][seeY] && map[seeX][seeY] != 0) {
                    queue.offer(new Location(seeX, seeY));
                    visited[seeX][seeY] = true;
                }
            }
        }
    }

    public static boolean checkLocation(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m)
            return false;
        return true;
    }
}