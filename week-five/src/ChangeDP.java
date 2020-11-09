import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int money, int[] coins) {
        int[] minNumCoins = new int[money + 1];
        minNumCoins[0] = 0;
        for (int m = 1; m <= money; m++) {
            minNumCoins[m] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (m >= coin) {
                    int t = minNumCoins[m - coin] + 1;
                    if (t < minNumCoins[m]) {
                        minNumCoins[m] = t;
                    }
                }
            }
        }

        return minNumCoins[money];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m, new int[]{1, 3, 4}));
    }
}

