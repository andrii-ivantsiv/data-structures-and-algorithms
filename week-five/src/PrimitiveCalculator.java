import java.util.*;

public class PrimitiveCalculator {

    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static Collection<Integer> dpSequence(int n) {
        int[] parents = new int[n + 1];
        int[] minOps = new int[n + 1];
        minOps[0] = 0;

        int currentParent;
        int currentMinOps;
        for (int k = 1; k < n + 1; k++) {
            currentParent = k - 1;
            currentMinOps = minOps[currentParent] + 1;

            int parent;
            int numOps;

            if (k % 3 == 0) {
                parent = k / 3;
                numOps = minOps[parent] + 1;
                if (numOps < currentMinOps) {
                    currentParent = parent;
                    currentMinOps = numOps;
                }
            }

            if (k % 2 == 0) {
                parent = k / 2;
                numOps = minOps[parent] + 1;
                if (numOps < currentMinOps) {
                    currentParent = parent;
                    currentMinOps = numOps;
                }
            }

            parents[k] = currentParent;
            minOps[k] = currentMinOps;
        }

        TreeSet<Integer> numbers = new TreeSet<>();
        int k = n;
        while (k > 0) {
            numbers.add(k);
            k = parents[k];
        }

        return numbers;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Collection<Integer> sequence = dpSequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

