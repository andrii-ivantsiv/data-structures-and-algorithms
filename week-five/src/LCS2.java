import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int startPoint = 0;
        List<Integer> subSequence = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = startPoint; j < b.length; j++) {
                if (a[i] == b[j]) {
                    startPoint = j + 1;
                    subSequence.add(a[i]);
                    break;
                }
            }
        }

        return subSequence.size();
    }

    private static int lcs2DP(int[] a, int[] b) {
        int[][] matrix = new int[a.length + 1][b.length + 1];
        matrix[0][0] = 0;
        for (int i = 1; i <= a.length; i++) {
            matrix[i][0] = a[i - 1];
        }
        System.arraycopy(b, 0, matrix[0], 1, b.length);

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                int previousA = j - 1 == 0 ? 0 : matrix[i][j - 1];
                if (a[i - 1] == b[j - 1]) {
                    matrix[i][j] = previousA + 1;
                } else {
                    int previousB = i - 1 == 0 ? 0 : matrix[i - 1][j];
                    matrix[i][j] = Integer.max(previousA, previousB);
                }
            }
        }

        return matrix[a.length][b.length];
    }

    private static int lcs2DPImproved(int[] a, int[] b) {
        int[][] matrix = new int[a.length + 1][b.length + 1];

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Integer.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }

        return matrix[a.length][b.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2DPImproved(a, b));
    }
}

