import java.util.*;

public class Main {

    static int n, count1, count2;
    static char[][] map;
    static boolean[][] seen;
    static int[] aroundX = { 1, -1, 0, 0 };
    static int[] aroundY = { 0, 0, -1, 1 };

    static class Location {
        int x, y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean checkColors(boolean isNormal, char curColor, char otherColor) {
        if (!isNormal && (curColor == 'R' || curColor == 'G') && (otherColor == 'R' || otherColor == 'G')) {
            return true;
        } else {
            return curColor == otherColor;
        }
    }

    static void bfs(boolean isNormal) {

        Queue<Location> queue;
        char color;
        seen = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!seen[i][j]) {

                    if (isNormal) {
                        count1++;
                    } else {
                        count2++;
                    }
                    seen[i][j] = true;
                    queue = new LinkedList<Location>();
                    queue.add(new Location(i, j));
                    color = map[i][j];

                    while (!queue.isEmpty()) {
                        Location location = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int ax = location.x + aroundX[k];
                            int ay = location.y + aroundY[k];

                            if (ax < 0 || ax >= n || ay < 0 || ay >= n) {
                                continue;
                            }

                            if (checkColors(isNormal, color, map[ax][ay]) && !seen[ax][ay]) {
                                seen[ax][ay] = true;
                                queue.add(new Location(ax, ay));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // INPUT ~
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        map = new char[n][n];

        boolean isNormal = true;

        for (int i = 0; i < n; i++) {
            char[] temp = scanner.next().toCharArray();
            map[i] = temp;
        }
        // ~ INPUT

        bfs(isNormal); // BFS :: 정상
        bfs(!isNormal); // BFS :: 적록색약

        // OUPUT
        System.out.println(count1 + " " + count2);
        scanner.close();
    }

}
