import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Partition3 {
    private static int partition3(int[] array) {

        int max = 0;
        for (int i : array) {
            max += i;
        }
        if (max % 3 == 0) {
            max /= 3;
        } else {
            return 0;
        }

        int[][] matrix = new int[array.length + 1][max + 1];
        matrix[0][0] = 0;
        for (int i = 1; i <= array.length; i++) {
            matrix[i][0] = array[i - 1];
        }
        for (int j = 1; j <= max; j++) {
            matrix[0][j] = j;
        }
        for (int i = 1; i <= array.length; i++) {
            for (int j = 1; j <= max; j++) {
                matrix[i][j] = matrix[0][j] - matrix[i][0];
            }
        }

        int groupsCount = 0;
        Map<Integer, Boolean> alreadyComputed = new HashMap<>();
        for (int k = 1; k <= array.length; k++) {

            int j = max;
            while (alreadyComputed.size() <= array.length) {
                int minValue = Integer.MAX_VALUE;
                int minValueIndex = Integer.MAX_VALUE;

                for (int x = 1; x <= array.length; x++) {
                    if (alreadyComputed.getOrDefault(x, false)) {
                        continue;
                    }

                    if (matrix[x][j] >= 0 && matrix[x][j] < minValue) {
                        minValue = matrix[x][j];
                        minValueIndex = x;
                    }
                }

                if (minValue == 0) {
                    groupsCount++;
                    alreadyComputed.put(minValueIndex, true);
                    break;
                }

                if (minValue == Integer.MAX_VALUE) {
                    break;
                }

                j = minValue;
                alreadyComputed.put(minValueIndex, true);
            }

            if (groupsCount >= 3) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        /*Random random = new Random();
        while (true) {
            int[] testArray = new int[random.nextInt(6)];
            for (int i = 0; i < testArray.length; i++) {
                testArray[i] = random.nextInt(31) + 1;
            }

            if (testArray.length < 1) {
                continue;
            }

            int p1 = partition3(testArray);
            int p2 = partition3FromWiki(testArray);
            if (p1 != p2) {
                System.out.println("Wrong: " + Arrays.toString(testArray) + " p1=" + p1 + " p2=" + p2);
                return;
            } else {
                System.out.println(Arrays.toString(testArray) + " p1=" + p1 + " p2=" + p2);
            }
        }*/

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3FromWiki(A));
    }

    private static int partition3FromWiki(int[] array) {
        int size = array.length;
        int sum = 0;
        for (int number : array) {
            sum += number;
        }
        int middle;
        if (sum % 3 == 0) {
            middle = sum / 3;
        } else {
            return 0;
        }

        boolean[][] matrix = new boolean[middle + 1][size + 1];
        for (int j = 0; j <= size; j++) {
            matrix[0][j] = true;
        }

        for (int i = 1; i <= middle; i++) {
            for (int j = 1; j <= size; j++) {
                if (i - array[j - 1] >= 0) {
                    matrix[i][j] = matrix[i][j - 1] || matrix[i - array[j - 1]][j - 1];
                } else {
                    matrix[i][j] = matrix[i][j - 1];
                }
            }
        }

        return matrix[middle][size] ? 1 : 0;
    }

}

