import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.math.BigInteger.valueOf;

/**
 * function fib1(n)
 * if n=0: return 0
 * if n=1: return 1
 * return fib1(n − 1) + fib1(n − 2)
 *
 * T(n)=3+T(n−1)+T(n−2)
 */
public class FibonacciComputationStepsCounter {

    public static void main(String[] args) {
        List<BigInteger> fibonacciComputationCalls = new ArrayList<>();

        fibonacciComputationCalls.add(valueOf(1));
        fibonacciComputationCalls.add(valueOf(1));
        //T(200)
        BigInteger three = valueOf(3);
        for (int i = 2; i < 198; i++) {
            fibonacciComputationCalls.add(three
                    .add(fibonacciComputationCalls.get(i - 1))
                    .add(fibonacciComputationCalls.get(i - 2)));
        }

        BigInteger fibonacciComputationCallsCount = fibonacciComputationCalls.get(fibonacciComputationCalls.size() - 1);
        System.out.println(fibonacciComputationCallsCount);
        //2^x >= T(200)
        BigInteger two = valueOf(2);
        for (int i = 2; ; i++) {
            BigInteger powOfTwo = two.pow(i);
            if (powOfTwo.compareTo(fibonacciComputationCallsCount) >= 0) {
                System.out.println(format("2^%s = %s > fibonacci calls = %s", i, powOfTwo, fibonacciComputationCallsCount));
                System.out.println(format("2^%s = %s < fibonacci calls = %s", i - 1, two.pow(i - 1), fibonacciComputationCallsCount));
                break;
            }
        }
    }
}
