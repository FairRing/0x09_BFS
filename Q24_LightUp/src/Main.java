import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {

    static class Room {
        private Point from;
        private boolean isLightOn;
        private ArrayList<Point> to;

        public Room(int x, int y, boolean isLightOn){
            this.from = new Point(x, y);
            this.isLightOn = isLightOn;
            to = new ArrayList<Point>();
        }
        
    }
    static int N, M, countLights = 0;
    static Room[][] map;
    static boolean[][] visited;
    static int[] aroundX = {1,-1,0,0}, aroundY = {0,0,-1,1};
    static Queue<Room> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");

        N = 1 + Integer.parseInt(input[0]); // statr point = 1,1
        M = Integer.parseInt(input[1]); // 
        map = new Room[N][N];
        visited = new boolean[N][N];

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                Room info;
                
                if (i==1&&j==1) {
                    info = new Room(i, j, true);
                    countLights++;
                } else {
                    info = new Room(i, j, false);
                }

                map[i][j] = info;
            }
        }
        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int element0 = Integer.parseInt(input[0]);
            int element1 = Integer.parseInt(input[1]);
            int element2 = Integer.parseInt(input[2]);
            int element3 = Integer.parseInt(input[3]);

            map[element0][element1].to.add(new Point(element2, element3));
        }

        bfs();
    }

    private static void bfs() {
        queue.offer(map[1][1]);
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            Room now = queue.poll();


            if(!now.to.isEmpty()){
                for (int i = 0; i < now.to.size(); i++) {

                    
                    queue.offer(map[now.to.get(i).x][now.to.get(i).y]);
                    map[now.to.get(i).x][now.to.get(i).y].isLightOn = true;

                    // countLights++;
                }
            }
        }

        
    
    }

   

}





        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < N; j++) {
        //         if (i==0||j==0) {
        //             burn[i][j] = -999; // wall;
        //         }
        //         else if (i==1&&j==1) {
        //              burn[i][j] = 1; // wall;
        //         } else {

        //         }
        //     }
        // }


/*


3 6
1 1 1 2
2 1 2 2
1 1 1 3
2 3 3 1
1 3 1 2
1 3 2 1

> 

불   0   0
0   0   0
0   0   0


 */



