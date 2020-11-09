import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciHuge {

    private static BigInteger getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return BigInteger.valueOf(n);

        BigInteger previous = BigInteger.ZERO;
        BigInteger current = BigInteger.ONE;

        for (long i = 0; i < n - 1; ++i) {
            BigInteger tmp_previous = previous;
            previous = current;
            current = tmp_previous.add(current);
        }

        return current.mod(BigInteger.valueOf(m));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeNaive(n, m));
    }
}
