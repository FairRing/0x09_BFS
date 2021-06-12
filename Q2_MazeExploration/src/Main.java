import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    // ax, ay : 동, 서, 남, 북
    static int[] ax = {1,-1,0,0};
    static int[] ay = {0,0,-1,1};

    public static void bfs(int x, int y){

        Queue<int[]> seen = new LinkedList<>();
        seen.offer(new int[]{x,y});

        while (!seen.isEmpty()) {
            int[] location = seen.poll();
            visited[x][y] = true; // visited!

            // check around...
            for (int point = 0; point < 4; point++) {
                int pX = location[0] + ax[point];
                int pY = location[1] + ay[point];
            

                if (pX > -1 && pX < n && pY > -1 && pY < m) { // just maze's range...
                    if (maze[pX][pY]!=0) { // is way? 1==true...
                        if (!visited[pX][pY]) { // is before visited? , pass condition : !true...
                            seen.offer(new int[] {pX,pY});
                            visited[pX][pY] = true;
                            maze[pX][pY] = maze[location[0]][location[1]] + 1; // before search cycle value +1;
                        }   
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());
        maze = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            token = new StringTokenizer(reader.readLine());
            String line = token.nextToken();
            for (int j = 0; j < m; j++) {
                maze[i][j] = Character.getNumericValue((line.charAt(j)));
            }
        }

        bfs(0,0);
        System.out.println(maze[n-1][m-1]);
    }
}



// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.Queue;
// import java.util.StringTokenizer;

// public class Main {

//     static int N;
//     static int M;
//     static int[][] maze;
//     static boolean[][] visited;

//     static int[] aroundX = { 1, -1, 0, 0 };
//     static int[] aroundY = { 0, 0, -1, 1 };

//     public static void main(String args[]) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         N = Integer.parseInt(st.nextToken());
//         M = Integer.parseInt(st.nextToken());

//         maze = new int[N][M];
//         visited = new boolean[N][M];

//         for (int i = 0; i < N; i++) {
//             st = new StringTokenizer(br.readLine());
//             String line = st.nextToken();
//             for (int j = 0; j < M; j++) {
//                 maze[i][j] = Character.getNumericValue(line.charAt(j));
//             }
//         }

//         bfs(0, 0);

//         System.out.println(maze[N - 1][M - 1]);
//     }

//     public static void bfs(int i, int j) {
//         Queue<int[]> checked = new LinkedList<>();
//         checked.offer(new int[] { i, j });

//         while (!checked.isEmpty()) {
//             int location[] = checked.poll();
//             visited[i][j] = true;

//             for (int a = 0; a < 4; a++) {
//                 int p_x = location[0] + aroundX[a];
//                 int p_y = location[1] + aroundY[a];

//                 if (p_x >= 0 && p_y >= 0 && p_x < N && p_y < M) {
//                     if (maze[p_x][p_y] != 0 && !visited[p_x][p_y]) {
//                         checked.offer(new int[] { p_x, p_y });
//                         visited[p_x][p_y] = true;
//                         maze[p_x][p_y] = maze[location[0]][location[1]] + 1;
//                     }
//                 }
//             }
//         }
//     }
// }