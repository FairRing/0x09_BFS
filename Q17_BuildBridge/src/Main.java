import java.io.*;
import java.util.*;


public class Main {

    static class Location {
        int x, y, type, id; // i, j , 0||1, default = 0(undifined)
        public Location(int x, int y, int type, int id){
            this.x = x;
            this.y = y;
            this.type = type;
            this.id = id;
        }
    }

    static int n, bridgeSize;
    static int map[][], visited[][];
    static int aroundX[] = {1,-1,0,0}, aroundY[] = {0,0,-1,1};
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n  = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            String input[] = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 육지별로 묶자
        System.out.println(n);
    }

    public void countLands() {
        Queue<Location> queue = new LinkedList<Location>();
        ArrayList<Location> landInfo = new ArrayList<Location>();
        visited = new int[n][n];
        Location location;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int idMax;
                if (landInfo.isEmpty()) idMax = 1;
                else idMax = landInfo.get(landInfo.size()-1).id;

                if (map[i][j] == 1) {
                    location = new Location(i, j, map[i][j], idMax);
                    landInfo.add(location);
                    queue.offer(location);
                }
                
                // pm9:54 cut... try again...
                for (int k = 0; k < 4; k++) {
                    int ax = i + aroundX[k]; 
                    int ay = j + aroundY[k];

                    if ((-1 < ax) && (ax < n) && (-1 < ay) && (ay < n)) {
                        
                    }
                }

                
            }
        }
        
    }
}
