import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int vertical;
    static int horizontal;
    static int[][] paper;

    static int[] x = { -1, 1, 0, 0 };
    static int[] y = { 0, 0, -1, 1 };

    static boolean[][] visited;
    static int count;
    static int tempPicture;

    static ArrayList<Integer> pictures = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] verticalAndHorizontal = br.readLine().split(" ");
        vertical = Integer.parseInt(verticalAndHorizontal[0]);
        horizontal = Integer.parseInt(verticalAndHorizontal[1]);

        paper = new int[vertical][horizontal];
        visited = new boolean[vertical][horizontal];

        for (int i = 0; i < vertical; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < horizontal; j++) {
                paper[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        // finished input.. (set vertical, horizontal  >>> set papers)


        for (int i = 0; i < vertical; i++) {
            for (int j = 0; j < horizontal; j++) {

                if (paper[i][j] == 1 && !visited[i][j]) {
                    tempPicture = 1;
                    dfs(i, j);
                    count++;
                    pictures.add(tempPicture);
                }
            }
        }

        System.out.println(count);

        try {
            System.out.println(Collections.max(pictures));
        } catch (Exception e) {
            System.out.println(0);
        }

    }

    // DFS 
    static void dfs(int k1, int k2) {
        visited[k1][k2] = true;

        for (int i = 0; i < 4; i++) {
            if 
            (
                k1 + x[i] >= 0 && k1 + x[i] < vertical && 
                k2 + y[i] >= 0 && k2 + y[i] < horizontal && 
                paper[k1 + x[i]][k2 + y[i]] == 1 && 
                !visited[k1 + x[i]][k2 + y[i]]
            ) {
                    tempPicture++;
                dfs(k1 + x[i], k2 + y[i]);
            }
        }
    }

}

























/*

// import java.io.BufferedReader;
// import java.io.InputStreamReader;

// public class Main {
//     public static void main(String[] args) throws Exception {

//         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

//         String paperSize = ""; // vertical & horizontal
//         int vertical = 0; // 세로
//         int horizontal = 0; // 가로
//         String paperLine = ""; // 가로 1줄
//         int[][] arrayPictures = null; // 각 그림 유무에 대한 2차원 배열

//         while (true) {

//             paperSize = bufferedReader.readLine();

//             vertical = Integer.parseInt(String.valueOf(paperSize.charAt(0)));
//             horizontal = Integer.parseInt(String.valueOf(paperSize.charAt(2)));

//             arrayPictures = new int[vertical+2][horizontal+2];

//             for (int i = 0; i < arrayPictures.length; i++) {

//                 paperLine = bufferedReader.readLine();
//                 String[] temp1 = paperLine.split(" ");

//                 for (int j = 0; j < arrayPictures[i].length; j++) {
//                   if ((i==0)||(i==vertical-1)||(j==0)||(j==horizontal-1)) {
//                     arrayPictures[i][j] = -1;
//                   }  else {
//                     arrayPictures[i][j] = Integer.parseInt(temp1[i-1]);
//                   }
//                 }
//             }

//             // finish setting pictures;
//             if (arrayPictures[vertical-1][horizontal-1] == -1) {
//                 break;
//             }

//         }
//     }
// }


*/