import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PlacingParentheses {
    private static String getMaximValue(String exp) {

        char[] numOps = exp.toCharArray();

        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        for (char numOp : numOps) {
            if (Character.isDigit(numOp)) {
                nums.add(Character.getNumericValue(numOp));
            } else {
                ops.add(numOp);
            }
        }

        int n = nums.size() + 1;
        BigInteger[][] m = new BigInteger[n][n];
        BigInteger[][] M = new BigInteger[n][n];

        for (int i = 1; i < n; i++) {
            BigInteger ni = BigInteger.valueOf(nums.get(i - 1));
            m[i][i] = ni;
            M[i][i] = ni;
        }

        int j;
        for (int s = 1; s < n - 1; s++) {
            for (int i = 1; i < n - s; i++) {
                j = i + s;
                BigInteger[] mm = minAndMax(i, j, m, M, ops);
                m[i][j] = mm[0];
                M[i][j] = mm[1];
            }
        }

        return M[1][n - 1].toString();
    }

    private static BigInteger[] minAndMax(int i, int j, BigInteger[][] m, BigInteger[][] M, List<Character> ops) {
        BigInteger minN = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger maxN = BigInteger.valueOf(Integer.MIN_VALUE);

        for (int k = i; k <= j - 1; k++) {
            BigInteger a = eval(M[i][k], M[k + 1][j], ops.get(k - 1));
            BigInteger b = eval(M[i][k], m[k + 1][j], ops.get(k - 1));
            BigInteger c = eval(m[i][k], M[k + 1][j], ops.get(k - 1));
            BigInteger d = eval(m[i][k], m[k + 1][j], ops.get(k - 1));

            minN = minN.min(a).min(b).min(c).min(d);
            maxN = maxN.max(a).max(b).max(c).max(d);
        }

        return new BigInteger[]{minN, maxN};
    }

    private static BigInteger eval(BigInteger a, BigInteger b, char op) {
        if (op == '+') {
            return a.add(b);
        } else if (op == '-') {
            return a.subtract(b);
        } else if (op == '*') {
            return a.multiply(b);
        } else {
            assert false;
            return BigInteger.ZERO;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

