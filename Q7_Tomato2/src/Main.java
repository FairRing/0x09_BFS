import java.io.*;
import java.util.*;

/** NOTE ********************
 input  [][][]  설명
 m      x       가로
 n      y       세로
 h      z       높이
 ****************************    
    tomatoBoxes's elements
 */

public class Main {
    static int n, m, h;
    static int[][][] tomatoBoxes;   
    static boolean[][][] seen;
    static int[] around_x = { 1, -1, 0, 0, 0, 0 };
    static int[] around_y = { 0, 0, 1, -1, 0, 0 };
    static int[] around_z = { 0, 0, 0, 0, 1, -1 };
    
    // NOTE CLASS TOMATO
    static class Tomato {
        int x, y, z, day;
        public Tomato(int x,int y,int z,int day) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.day = day;
        }
    }

    // NOTE BFS
    public static void bfs() {
        Queue<Tomato> ripe = new LinkedList<Tomato>();
        int day = 0;

        // ripe tomato
        for (int h_ = 0; h_ < h; h_++) {  // 높이
            for (int n_ = 0; n_ < n; n_++) { // 세로
                for (int m_ = 0; m_ < m; m_++) { // 가로
                    if (tomatoBoxes[h_][n_][m_] == 1) {
                        ripe.offer(new Tomato(m_, n_, h_, day));
                    }
                }
            }
        }
        // SECTION START FIND RIPE TOMATOS  
        while (!ripe.isEmpty()) {
            // NOTE before ripe tomato...
            Tomato tomato = ripe.poll();
            day = tomato.day;

            for (int i = 0; i < 6; i++) {
                // NOTE 이전 익은 토마토의 주변(동 서 남 북 위 아래) 좌표 확인
                int seeX = tomato.x + around_x[i];  // m
                int seeY = tomato.y + around_y[i];  // n
                int seeZ = tomato.z + around_z[i];  // h

                if (0 <= seeX && seeX < m && 0 <= seeY && seeY < n && 0 <= seeZ && seeZ < h) { // NOTE 전체 상자 범위 체크
                    if (tomatoBoxes[seeZ][seeY][seeX] == 0) { // NOTE TOMATO's STATUS : 1=익음  0=안익음  -1=없음
                        tomatoBoxes[seeZ][seeY][seeX] = 1;  // NOTE 토마토익힘
                        ripe.add(new Tomato(seeX, seeY, seeZ, day+1)); // NOTE 익은 토마토 큐 삽입
                    }
                }
            }
        }
        // SECTION END FIND RIPE TOMATOS
        
        
        // SECTION OUTPUT
        if (checkTomatoStatus()) { // NOTE 안익은토마토(value = 0)가 없다(-1을 제외한 모든곳이 익음)
            System.out.println(day);
        } else { // NOTE 안익은토마토(value = 0)가 있다
            System.out.println(-1);
        }
    }

    public static boolean checkTomatoStatus() {
        // SECTION START CHECK ALL TOMATOS RIPE
        for (int h_ = 0; h_ < h; h_++) {  // 높이
            for (int n_ = 0; n_ < n; n_++) { // 세로
                for (int m_ = 0; m_ < m; m_++) { // 가로
                    if (tomatoBoxes[h_][n_][m_] == 0) return false; // NOTE 안익은토마토
                }
            }
        }
        // SECTION END  CHECK ALL TOMATOS RIPE
        return true;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // SECTION START INPUT
        StringTokenizer token = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(token.nextToken());
        n = Integer.parseInt(token.nextToken());
        h = Integer.parseInt(token.nextToken());
        tomatoBoxes = new int[h][n][m];
        seen = new boolean[h][n][m];

        for (int h_ = 0; h_ < h; h_++) {  // 높이
            for (int n_ = 0; n_ < n; n_++) { // 세로
                token = new StringTokenizer(reader.readLine());
                for (int m_ = 0; m_ < m; m_++) { // 가로
                    tomatoBoxes[h_][n_][m_] = Integer.parseInt(token.nextToken());
                }
            }
        }
        bfs();
        // SECTION END INPUT
    }
}
