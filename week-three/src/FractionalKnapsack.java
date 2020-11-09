import java.text.DecimalFormat;
import java.util.*;

public class FractionalKnapsack {
    private static String getOptimalValue(int capacity, int[] values, int[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("values and weights has a different size");
        }

        if (capacity == 0) {
            return "0.0000";
        }

        double result = 0;
        Map<Double, Integer> wightValues = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < values.length; i++) {
            wightValues.put((double) values[i] / weights[i], i);
        }

        for (Map.Entry<Double, Integer> wightValuesEntry : wightValues.entrySet()) {
            int valueIndex = wightValuesEntry.getValue();
            if (capacity > 0 && capacity >= weights[valueIndex]) {
                result += values[valueIndex];
                capacity -= weights[valueIndex];
            } else if (capacity > 0 && capacity < weights[valueIndex]) {
                result += capacity * wightValuesEntry.getKey();
                break;
            }
        }

        return new DecimalFormat(".0000").format(result);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
