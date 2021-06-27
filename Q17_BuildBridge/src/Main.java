import java.io.*;
import java.util.*;

public class Main {

    static class Location {
        int x, y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, bridgeMinSize;
    static int[][] map, aroudXY = {{1,0}, {-1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
    static Queue<Location> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = null;

        n = Integer.parseInt(reader.readLine());

        queue = new LinkedList<>();
        map = new int[n][n];
        visited = new boolean[n][n];
        bridgeMinSize = Integer.MAX_VALUE;

        for (int x = 0; x < n; ++x) {
            token = new StringTokenizer(reader.readLine());
            for (int y = 0; y < n; ++y) {
                map[x][y] = Integer.parseInt(token.nextToken());
            }
        }

        giveId();

        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                if (map[x][y] == 0) continue;

                init();

                queue.offer(new Location(x, y));
                visited[x][y] = true;
                int bridgeSize = findMinimumBridgeSize(map[x][y]);

                if (bridgeSize == -1) continue;

                bridgeMinSize = bridgeMinSize > bridgeSize ? bridgeSize : bridgeMinSize;
            }
        }
        System.out.println(bridgeMinSize);
    }

    private static int findMinimumBridgeSize(int start) {
        int bridgeSize = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; ++s) {
                Location latest = queue.poll();

                if (map[latest.x][latest.y] != 0 && map[latest.x][latest.y] != start) {
                    return bridgeSize;
                }

                for (int a = 0; a < 4; ++a) {
                    int ax = latest.x + aroudXY[a][0];
                    int ay = latest.y + aroudXY[a][1];

                    if (ax < 0 || ax >= n || ay < 0 || ay >= n) continue;
                    if (visited[ax][ay] || map[ax][ay] == start) continue;

                    queue.offer(new Location(ax, ay));
                    visited[ax][ay] = true;
                }
            }
            bridgeSize++;
        }
        return -1;
    }

    private static void init() {
        queue.clear();

        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                visited[x][y] = false;
            }
        }
    }

    private static void giveId() {
        int id = 2;

        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < n; ++y) {
                if (visited[x][y] || map[x][y] == 0) continue;
                
                map[x][y] = id;
                queue.offer(new Location(x, y));
                visited[x][y] = true;

                while (!queue.isEmpty()) {
                    Location latest = queue.poll();

                    for (int a = 0; a < 4; ++a) {
                        int ax = latest.x + aroudXY[a][0];
                        int ay = latest.y + aroudXY[a][1];
                        if (ax < 0 || ax >= n || ay < 0 || ay >= n || visited[ax][ay] || map[ax][ay] == 0) continue;
                        if (map[ax][ay] == 1) {
                            queue.offer(new Location(ax, ay));
                            map[ax][ay] = id;
                            visited[ax][ay] = true;
                        }
                    }
                }
                id++;
            }
        }
    }

}
