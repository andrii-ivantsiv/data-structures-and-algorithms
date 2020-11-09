import java.math.BigInteger;
import java.util.*;

import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;

public class DotProduct {

    private static String maxDotProduct(int[] a, int[] b) {
        BigInteger result = ZERO;

        Arrays.sort(a);
        Arrays.sort(b);

        for (int i = 0; i < a.length; i++) {
            result = result.add(valueOf(a[i]).multiply(valueOf(b[i])));
        }

        return result.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}

