import java.util.*;

import static java.lang.Integer.min;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int m = s.length() + 1;
        int n = t.length() + 1;

        int[][] e = new int[m][n];

        for (int i = 0; i < m; i++) {
            e[i][0] = i;
        }
        for (int j = 0; j < n; j++) {
            e[0][j] = j;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                e[i][j] = min(1 + e[i - 1][j], min(1 + e[i][j - 1], e[i - 1][j - 1] + diff(s, i, t, j)));
            }
        }

        return e[m - 1][n - 1];
    }

    static int diff(String s, int i, String t, int j) {
        return s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1;
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
