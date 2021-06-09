import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Location
 */
class Location {
    int x, y;

    public Location(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }
}
public class Main {

    /* init */
    static int[][] map;           // map
    static boolean[][] isVisited; // map 요소 방문여부 
    static int vertical;          // map 세로
    static int horizontal;        // map 가로
    /*      (0 ,1)
    (-1,0)  (0, 0)  (1,0)
            (0,-1)        */
    static Queue<Location> location;
    static int x_[] = {1, 0, -1, 0};
    static int y_[] = {0, 1, 0, -1};
    /* result */
    static int pictureCount = 0;
    static int maxSizePicture = 0;
 
    public static void main(String[] args) throws Exception {
    
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        /* BFS START */
        // INPUT CONSOLE 1st LINE... >>> set [][]
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        vertical = Integer.parseInt(stringTokenizer.nextToken());
        horizontal = Integer.parseInt(stringTokenizer.nextToken());
        map = new int[vertical][horizontal];
        isVisited = new boolean[vertical][horizontal];

        // INPUT CONSOLE 2nd LINE... >>> pictures location info
        for (int i = 0; i < vertical; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			for (int j = 0; j < horizontal; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
        for (int i = 0; i < vertical; i++) {
			for (int j = 0; j < horizontal; j++) {
				if (map[i][j] == 1 && !isVisited[i][j]) {
					BFS(i, j);
				}
			}
		}

        // OUTPUT RESULT
        System.out.println(pictureCount);
		System.out.println(maxSizePicture);
    }

    public static void BFS(int x, int y) {

		location = new LinkedList<>();
		location.add(new Location(x, y));
		
        int count = 0;
		
        while (!location.isEmpty()) {
			Location target = location.poll();		
			count++;

			for (int loc = 0; loc < 4; loc++) {

                int x__ = target.x + x_[loc];
                int y__ = target.y + y_[loc];
                
                if (x__ < 0 || x__ >= vertical || y__ < 0 || y__ >= horizontal) continue; // map의 바깥인지 체크
                if (isVisited[x__][y__] || map[x__][y__] == 0) continue; // 이미 방문한 위치인지, 그림이 아닌 위치인지 체크
                
                isVisited[x__][y__] = true;
                location.add(new Location(x__, y__));
            }
		}
		
		pictureCount++;
		if(count>1) count--;
		maxSizePicture = Math.max(maxSizePicture, count);	
	}
}






/*


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
    static void dfs(int v, int h) {
        visited[v][h] = true;

        for (int i = 0; i < 4; i++) {
            if 
            (
                v + x[i] >= 0 && v + x[i] < vertical && 
                h + y[i] >= 0 && h + y[i] < horizontal && 
                paper[v + x[i]][h + y[i]] == 1 && 
                !visited[v + x[i]][h + y[i]]
            ) {
                    tempPicture++;
                dfs(v + x[i], h + y[i]);
            }
        }
    }

}
*/



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