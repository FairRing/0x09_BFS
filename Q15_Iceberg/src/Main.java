import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int n, m, answer = 0; // 세로, 가로, 정답
    static int arr[][], map[][];
    static int aroundX[] = {1,-1,0,0}, aroundY[] = {0,0,-1,1};
    static ArrayList<Point> icebergs = new ArrayList<Point>(); // 빙산

    //연결 체크
    public static int getNumberOfIcebergs(Point icebergLocation) {
        int count = 0;
        boolean visit[][] = new boolean[n][m];
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(icebergLocation);
        visit[icebergLocation.y][icebergLocation.x] = true;

        while(!queue.isEmpty()) { 
            Point point = queue.poll();
            count++;
            for(int i=0; i<4; i++) {
                int ay = point.y + aroundY[i];
                int ax = point.x + aroundX[i];
                if(0<=ay && ay<n && 0<=ax && ax<m ) {  // check border lines...
                    if(arr[ay][ax]!=0 && !visit[ay][ax]) { // find new iceberg location...
                        visit[ay][ax]=true;
                        queue.add(new Point(ax,ay));
                    }
                }
            }
        }
        return count;
    }
 
    public static void bfs() {
        Queue<Point> queue = new LinkedList<Point>();

        for (int i = 0; i < icebergs.size(); i++) {
            Point point = icebergs.get(i);
            queue.add(point);
        }

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            answer++;

            for (int i = 0; i < queueSize; i++) {
                Point point = queue.poll();
                int count = 0; // number of contact with sea...
                for (int j = 0; j < 4; j++) {
                    int ax = point.x + aroundX[j];
                    int ay = point.y + aroundY[j];
                    if (0 <= ax && ax < m && 0 <= ay && ay < n && arr[ay][ax] == 0)count++; // check about out of range & is location of sea...
                }
                if (map[point.y][point.x] <= count) { // melted away
                    map[point.y][point.x] = 0; // minimum of iceberg's value is 0!
                } else {
                    map[point.y][point.x] = map[point.y][point.x] - count;
                    queue.add(point);
                }
            }
            // copy current status...
            for (int j = 0; j < n; j++) {
                for (int j2 = 0; j2 < m; j2++) {
                    arr[j][j2] = map[j][j2];
                }
            }

            if (queue.peek() != null && getNumberOfIcebergs(queue.peek()) != queue.size()) { // check icebergs exist
                System.out.println(answer); // ANSWER
                return;
            }
        }
        System.out.println(0); // ANSWER
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        arr = new int[n][m];
        map = new int[n][m];
        for (int i = 0; i < n; i++) { // init loop start~
            input = reader.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int element = Integer.parseInt(input[j]);
                arr[i][j] = element;
                map[i][j] = element;
                if (element != 0) icebergs.add(new Point(j,i));
            }
        } // init loop end.

        // bfs start~
        if (getNumberOfIcebergs(icebergs.get(0)) != icebergs.size()) { // check icebergs exist
            System.out.println(0); // ANSWER
        } else {
            bfs();
        } // bfs end.
    }
}
