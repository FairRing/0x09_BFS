import java.io.*;
import java.util.*;

public class Main {
    
    static int N, K, MIN = 0, MAX = 100000, second = 0;
    static int[] line = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int X = N;        
        while (true) {
            int distance = difference(X);
            int cases[] = new int[3];

            if (distance == 0) {
                break;
            } else {

                cases[0] = moveForward(X);
                cases[1] = moveBackward(X);
                cases[2] = teleport(X);
                Arrays.sort(cases);
                
                value1 = moveForward(X);
                value2 = moveBackward(X);
                value3 = teleport(X);
    
            }

            
            
            


        }

    }



    private static int teleport(int X) {
        if(X*2 > MAX) return -999999;
        return X*2;
    }
    private static int moveForward(int X) {
        if(X+1 > MAX) return -999999;
        return X+1;
    }
    private static int moveBackward(int X) {
        if(X-1 < MIN) return -999999;
        return X-1;
    }
    private static int difference(int X) {
        return K-X;
    }
}


