import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int x;

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

        while (sum != x) {
            /**
             * cur 64, sum 0
             * cur 32, sum 0
             * cur 16, sum 16
             * cur 8, sum 16
             * cur 4 sum 20
             * cur 2 sum 22
             * cur 1 sum 23
             */
            cur /= 2;

            if (sum + cur <= x) {
                sum += cur;
                count++;
            }
        }

        System.out.println(count);
    }
}

