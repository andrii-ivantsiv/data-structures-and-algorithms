import java.util.*;

import static java.util.Collections.singleton;

public class DifferentSummands {
    private static Set<Integer> optimalSummands(int n) {
        if (n == 1) {
            return singleton(n);
        }

        if (n == 2) {
            return singleton(2);
        }

        TreeSet<Integer> summands = new TreeSet<>();

        Set<Integer> primes = new TreeSet<>();
        boolean isPrime;
        for (int i = 1; i <= n; i++) {
            isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }

        int num = n;
        for (Iterator<Integer> iterator = primes.iterator(); iterator.hasNext(); ) {
            int prime = iterator.next();
            int reminder = num - prime;
            if (primes.contains(reminder) || reminder == 0) {
                summands.add(prime);
                iterator.remove();
                num = reminder;
                if (num == 0) {
                    break;
                }
                iterator = primes.iterator();
            }
        }

        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Set<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

