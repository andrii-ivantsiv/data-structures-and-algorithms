import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.math.BigInteger.valueOf;

public class LeastCommonMultiple {

    private static long lcmNaive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }

    private static long gcdV2(long a, long b) {
        long modOfAToB = a % b;

        if (modOfAToB == 0) {
            return b;
        } else {
            return gcdV2(b, modOfAToB);
        }
    }

    private static BigInteger lcmV2(int a, int b) {
        long gcd = gcdV2(a, b);
        return valueOf(a).multiply(valueOf(b)).divide(valueOf(gcd));

        /*BigInteger x = valueOf(a);
        BigInteger y = valueOf(b);
        BigInteger TWO = valueOf(2);

        if (x.mod(y).compareTo(BigInteger.ZERO) == 0) {
            return x;
        }

        if (y.mod(x).compareTo(BigInteger.ZERO) == 0) {
            return y;
        }

        BigInteger m = x.multiply(y);
        if (x.subtract(y).compareTo(BigInteger.ONE) == 0 || y.subtract(x).compareTo(BigInteger.ONE) == 0) {
            return m;
        }

        List<BigInteger> mx = new ArrayList<>();
        BigInteger l = BigInteger.ONE;
        while (true) {
            l = l.add(BigInteger.ONE);
            BigInteger e = x.multiply(l);
            mx.add(e);
            if(e.compareTo(m) > 0) {
                break;
            }
        }

        List<BigInteger> my = new ArrayList<>();
        l = BigInteger.ONE;
        while (true) {
            l = l.add(BigInteger.ONE);
            BigInteger e = y.multiply(l);
            my.add(e);
            if(e.compareTo(m) > 0) {
                break;
            }
        }

        for (BigInteger bigIntegerX : mx) {
            for (BigInteger bigIntegerY : my) {
                if (bigIntegerX.compareTo(bigIntegerY) == 0) {
                    return bigIntegerX;
                }
            }
        }

        return m;*/
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        //System.out.println(lcmNaive(a, b));
        System.out.println(lcmV2(a, b));
    }
}
