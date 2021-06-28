import java.io.*;
import java.util.*;

public class Main {
    
    static class Location {
        int x, y, z, count;

        public Location(int x, int y, int z, int count) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }

    static int L, R, C;
    static char[][][] building;
    static Location start, exit;
    static int[] aroundX = {-1, 1, 0, 0, 0, 0}, aroundY = {0, 0, -1, 1, 0, 0}, aroundZ = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
    
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input[] = reader.readLine().split(" ");

        }
    
    
    }
}