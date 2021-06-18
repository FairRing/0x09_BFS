
import java.io.*;
import java.util.*;

public class Main {
    static int n; // map size
    static int[][] map; // map
    static boolean[][] seen; // check map's element
    static ArrayList<Integer> subResult;
    static int[] aroundX = {1,-1,0,0}; 
    static int[] aroundY = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        // SECTION :: input start
        Scanner scanner = new Scanner(System.in);
        int MaxVlaueOfMap; // map 요소중 가장 높은 구간

        while (true) {
            n = scanner.nextInt();    
            if ((n>1)&&(n<101)) break;
        }
        map = new int[n][n];
        seen = new boolean[n][n];
        MaxVlaueOfMap = 0;
  
        for (int n1 = 0; n1 < n; n1++) { // init map
            for (int n2 = 0; n2 < n; n2++) { // init map
                map[n1][n2] = scanner.nextInt();
                if (map[n1][n2] > MaxVlaueOfMap)  MaxVlaueOfMap = map[n1][n2]; // init MaxVlaueOfMap
            }            
        }
        // SECTION :: input end

        // SECTION :: bfs start <<< check range = MaxVlaueOfMap(height) * map[].length * map[][].length
        subResult = new ArrayList<Integer>();
        int count;
        for (int h = 0; h < MaxVlaueOfMap; h++) {
            count = 0;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x]>h && !seen[y][x]) {
                        count+=1;
                        bfs(y,x,h);                        
                    }
                }    
            }
            for (boolean s[] : seen) Arrays.fill(s, false);
            subResult.add(count);
        }
        // SECTION :: bfs end

        // SECTION :: output start
        int result = Collections.max(subResult);
        System.out.println(result);
        scanner.close();
        // SECTION :: output end        

    }

    public static void bfs(int y, int x, int h) {
        seen[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ay = y + aroundY[i];
            int ax = x + aroundX[i];

            if (ay >= 0 && ax >= 0 && ay < map.length && ax < map.length) {
                if (map[ay][ax] > h && !seen[ay][ax])
                    bfs(ay, ax, h);
            }
        }
    }
}
