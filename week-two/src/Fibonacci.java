import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.math.BigInteger.*;
import static java.util.Collections.addAll;

public class Fibonacci {

    public static void main(String[] args) {
        //System.out.println(calculateFibonacciV1(new Scanner(System.in).nextLong()));
        //System.out.println(calculateFibonacciV2(new Scanner(System.in).nextLong()));
        System.out.println(calculateFibonacciV3(new Scanner(System.in).nextLong()));
    }

    private static long calculateFibonacciV1(long n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacciV1(n - 1) + calculateFibonacciV1(n - 2);
        }
    }

    private static String calculateFibonacciV2(long n) {
        if (n == 0L) {
            return ZERO.toString();
        }

        BigInteger previous = ZERO;
        BigInteger current = ONE;
        BigInteger sum;

        for (int i = 1; i < n; ++i) {
           sum = previous.add(current);
           previous = current;
           current = sum;
        }

        return current.toString();
    }

    private static int calculateFibonacciV3(long n) {
        if (n == 0L) {
            return 0;
        }

        BigInteger previous = ZERO;
        BigInteger current = ONE;
        BigInteger sum;

        for (int i = 1; i < n; ++i) {
            sum = previous.add(current).mod(TEN);
            previous = current;
            current = sum;
        }

        return current.intValue();
    }
}
