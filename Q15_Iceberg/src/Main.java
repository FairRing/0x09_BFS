import java.io.*;
import java.util.*;

public class Main {
    static int n, m; // 세로, 가로
    static int[][] iceberg;
    static int[][] seen; // true = 1  OR  false =0
    static int[] aroundX = {1,-1,0,0};
    static int[] aroundY = {0,0,-1,1};
    static int year = 0;// 빙산이 분리되는 최초 년도

    static class Point {
        int n, m, h;
        public Point (int n, int m, int h) {
            this.n = n; // 세로
            this.m = m; // 가로
            this.h = h; // 높이
        }
    }

    public static void bfs() {
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        iceberg = new int[n][m];
        seen = new int[n][m];

        for (int n_ = 0; n_ < n; n_++) {
            input = reader.readLine().split(" ");
            for (int m_ = 0; m_ < m; m_++) {
                iceberg[n][m] = Integer.parseInt(input[0]);
            }
        }
      
        bfs(); // <<< next... retry one more...

		// System.out.println(year);


    }
}
