import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.String.format;
import static java.math.BigInteger.valueOf;

public class MaxPairwiseProduct {

    static long getMaxPairwiseProductCorrect(int[] numbers) {
        int n = numbers.length;
        int firstMaxNumberIndex = 0;

        for (int i = 1; i < n; ++i) {
            if (numbers[firstMaxNumberIndex] < numbers[i]) {
                firstMaxNumberIndex = i;
            }
        }

        int secondMaxNumberIndex = firstMaxNumberIndex == 0 ? 1 : 0;

        for (int i = 1; i < n; ++i) {
            if (i != firstMaxNumberIndex && numbers[secondMaxNumberIndex] < numbers[i]) {
                secondMaxNumberIndex = i;
            }
        }

        return valueOf(numbers[firstMaxNumberIndex]).multiply(valueOf(numbers[secondMaxNumberIndex])).longValue();
    }

    static long getMaxPairwiseProduct(int[] numbers) {
        int n = numbers.length;

        int firstMaxNumIndex;
        int secondMaxNumIndex;
        if (numbers[0] >= numbers[1]) {
            firstMaxNumIndex = 0;
            secondMaxNumIndex = 1;
        } else {
            firstMaxNumIndex = 1;
            secondMaxNumIndex = 0;
        }

        for (int i = 2; i < n; ++i) {
            if (numbers[i] > numbers[firstMaxNumIndex]) {
                secondMaxNumIndex = firstMaxNumIndex;
                firstMaxNumIndex = i;
            }
            if (numbers[i] > numbers[secondMaxNumIndex] && i != firstMaxNumIndex) {
                secondMaxNumIndex = i;
            }
        }

        //System.out.println(format("First index %s Second index %s", firstMaxNumIndex, secondMaxNumIndex));

        return valueOf(numbers[firstMaxNumIndex]).multiply(valueOf(numbers[secondMaxNumIndex])).longValue();
    }

    public static void main(String[] args) {
        /*Random random = new Random();
        while (true) {
            int[] nums = random.ints(random.nextInt(100 - 1) + 2, 2, 100000).toArray();

            long correct = getMaxPairwiseProductCorrect(nums);
            long faster = getMaxPairwiseProduct(nums);
            if (correct != faster) {
                System.out.println("WRONG");
                System.out.println(Arrays.toString(nums));
                System.out.println(format("correct %s faster %s ", correct, faster));
                break;
            } else {
                System.out.println("OK");
                System.out.println(Arrays.toString(nums));
                System.out.println(format("correct %s faster %s ", correct, faster));
            }
        }*/

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
