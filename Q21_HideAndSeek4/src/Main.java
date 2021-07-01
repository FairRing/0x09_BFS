

/*
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 
동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 
만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 
순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
*/

/*
입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
*/

/*
출력
첫째 줄에 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
둘째 줄에 어떻게 이동해야 하는지 공백으로 구분해 출력한다.
*/

/*
예제 입력 1 
5 17
예제 출력 1 
4
5 10 9 18 17
*/

/*
예제 입력 2 
5 17
예제 출력 2 
4
5 4 8 16 17
 */

import java.io.*;
import java.util.*;

public class Main {

    static int N, K, second = Integer.MAX_VALUE;
    static int[] map = new int[100001]; 
    static boolean[] visited = new boolean[100001];
    static Queue<Integer> queue;
    static ArrayList<Integer> history;
    
    public static void main(String[] args) throws Exception {
        ///// INPUT ///////////////////////////////////////////////////////////////////////////
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        ///// BFS /////////////////////////////////////////////////////////////////////////////
        queue = new LinkedList<Integer>();
        visited[N] = true;
        queue.offer(N);
        history = new ArrayList<Integer>();
        bfs();
        ///// OUTPUT //////////////////////////////////////////////////////////////////////////
        System.out.println(second);
        ///// END /////////////////////////////////////////////////////////////////////////////
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            int current = queue.poll();
            ///// GOAL ////////////////////////////////////////////////////
            if (current == K) {

                StringBuilder sb = new StringBuilder();
        for (Integer his : history) sb.append(his != null ? his.toString() + " " : "");
        System.out.println(sb.toString());
        
                second = Math.min(second, map[current]);
                history = new ArrayList<>();
                break;
            }
            ///// MOVE-1 //////////////////////////////////////////////////
            if (0 <= current - 1 && !visited[current - 1]) {
                queue.offer(current - 1);
                visited[current - 1] = true;
                map[current - 1] = map[current] + 1;
                history.add(current - 1);
            }
            ///// MOVE*2 //////////////////////////////////////////////////
            if (current * 2 <= 100000 && !visited[current * 2]) {
                queue.offer(current * 2);
                visited[current * 2] = true;
                map[current * 2] = map[current];
                history.add(current * 1);
            }
            ///// MOVE+1 //////////////////////////////////////////////////
            if (current + 1 <= 100000 && !visited[current + 1]) {
                queue.offer(current + 1);
                visited[current + 1] = true;
                map[current + 1] = map[current] + 1;
                history.add(current + 1);
            }
        } // scope of loop...
    }
}


