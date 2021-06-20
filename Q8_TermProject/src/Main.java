import java.io.*;
import java.util.*;

public class Main {
    public static int t, n;
    public static int[] connect;
    public static boolean[] seen;
    public static boolean[] matched;
    public static int result;


    // SECTION :: bfs
    public static void bfs(int node) {
        int nextNode = connect[node];
        seen[node] = true;

        if (!seen[nextNode]) {
            bfs(nextNode);
        } else {
            if (!matched[nextNode]) {
                ++result;
                while (nextNode != node) {
                    ++result;
                    nextNode = connect[nextNode];
                }
            }
        }
        matched[node] = true;
    }
    public static void main(String[] args) throws Exception {
        // SECTION :: start input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        t = Integer.parseInt(reader.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(reader.readLine());
            connect = new int[n + 1];

            token = new StringTokenizer(reader.readLine(), " ");
            for (int j = 1; j < n + 1; j++) connect[j] = Integer.parseInt(token.nextToken());

            matched = new boolean[n + 1];
            seen = new boolean[n + 1];
            result = 0;
            // SECTION :: end input
        
            for (int j = 1; j <= n; j++) bfs(j);

            writer.write(String.valueOf(n - result) + "\n");// SECTION :: output
        }
        writer.flush();
    }
}