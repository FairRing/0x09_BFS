import java.io.*;
import java.util.*;

public class Main {
    
    static class Location {
        int x, y, z, count;

        public Location(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }

    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;
    static Location start, exit;
    static int[] aroundX = {-1, 1, 0, 0, 0, 0}, aroundY = {0, 0, -1, 1, 0, 0}, aroundZ = {0, 0, 0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input[] = reader.readLine().split(" "); // 빌딜 층(L, x) 행(R, y) 열(C, z)
            
            if (input[0].equals("0") && input[1].equals("0") && input[2].equals("0")) break; // EXIT.
            
            L = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);
            building = new char[L][R][C];
            start = exit = null;

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String buildingElements = reader.readLine();
                    for (int c = 0; c < C; c++) {
                        building[l][r][c] = buildingElements.charAt(c);
                        if (building[l][r][c]=='S') start = new Location(l,r,c,0); // START LOCATION
                        if (building[l][r][c]=='E') exit = new Location(l,r,c,0); // EXIT  LOCATION
                    }
                }
                reader.readLine(); // 층 구분
            }
            
            int escape = bfs(start);
            // OUTPUT
            if (escape < 0) System.out.println("Trapped!");
            else System.out.println("Escaped in "+escape+" minute(s).");
        }
    }

    private static int bfs(Location start) {
        visited = new boolean[L][R][C];
        visited[start.x][start.y][start.z] = true;
        Queue<Location> queue = new LinkedList<Location>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Location latest = queue.poll();

            for(int i=0; i<6; i++) {
                int x = latest.x+aroundX[i];
                int y = latest.y+aroundY[i];
                int z = latest.z+aroundZ[i];
                int count = latest.count+1;

                if(x==exit.x && y==exit.y && z==exit.z) return count; // GOAL!

                if(x>=0 && y>=0 && z>=0 && x<L && y<R && z<C && building[x][y][z]=='.' && !visited[x][y][z]) {
                    visited[x][y][z] = true;
                    queue.add(new Location(x, y, z, count));
                }
            }
        }
        return -1;
    }
}