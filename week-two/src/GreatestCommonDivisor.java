import java.util.Scanner;

public class GreatestCommonDivisor {

    private static int gcdNaive(long a, long b) {
        int current_gcd = 1;
        for (int d = 2; d <= a && d <= b; ++d) {
            if (a % d == 0 && b % d == 0) {
                if (d > current_gcd) {
                    current_gcd = d;
                }
            }
        }

        return current_gcd;
    }

    private static long gcdV2(long a, long b) {
        long modOfAToB = a % b;

        if (modOfAToB == 0) {
            return b;
        } else {
            return gcdV2(b, modOfAToB);
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        //System.out.println(gcdNaive(a, b));
        System.out.println(gcdV2(a, b));
    }
}
