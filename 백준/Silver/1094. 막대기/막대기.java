import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int x;
    static int[] arr = new int[65];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        x = Integer.parseInt(br.readLine());

        if (x == 64) {
            System.out.println(1);
            return;
        }

        int count = 0;
        int cur = 64;
        int sum = 0;
        
        while (cur != 1) {
            cur /= 2;

            if (cur <= x && sum + cur <= x) {
                sum += cur;
                count++;
            }
        }
        System.out.println(count);
    }
}

