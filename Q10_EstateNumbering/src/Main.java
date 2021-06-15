import java.util.*;
import java.io.*;
/*
 * NOTE 
 * # input ################################# 
 * - n : 길이가 n 인 정사각형(5≤N≤25)
 * - map[n][n] : 1,0으로 채워짐 
 * # output ################################ 
 * - vector.size : 1의 묶음 갯수 
 * - vector.elements : 각각의 1의 묶음 사이즈
 */
public class Main {
    private static int n;
    private static int[][] map;
    private static boolean[][] seen;
    private static int count;

    private static int[] aroundX = { 0, 1, 0, -1 };
    private static int[] aroundY = { 1, 0, -1, 0 };

    public static void main(String[] args) throws NumberFormatException, IOException {

        // SECTION INPUT
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n+1][n+1];
        seen = new boolean[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            String[] temp = (reader.readLine()).split("");
            for (int j = 0; j < temp.length; j++) map[i][j+1] = Integer.parseInt(temp[j]);
        }
        // SECTION DFS
        ArrayList<Integer> result = new ArrayList<Integer>();
        int estateCount = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!seen[i][j] && map[i][j] == 1) {
                    count = 0;
                    dfs(i, j);
                    result.add(count);
                    estateCount++;
                }
            }
        }
        // SECTION OUTPUT
        Collections.sort(result);
        result.add(0, estateCount);
        for (int i : result) System.out.println(i);
    }
    // NOTE DFS
    private static void dfs(int x, int y) {
        count++;
        seen[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int ax = x+aroundX[i];
            int ay = y+aroundY[i];

            if (ax < 1 || ay < 1 || ax > n || ay > n) continue;
            
            if (!seen[ax][ay] && map[ax][ay] == 1) {
                seen[ax][ay] = true;
                dfs(ax, ay);
            }
        }
    }
}