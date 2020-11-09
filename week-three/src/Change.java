import java.util.Scanner;

public class Change {

    static final int ONE_CENT = 1;
    static final int FIVE_CENTS = 5;
    static final int TEN_CENTS = 10;

    private static int getChange(int m) {
        int tenCentsCount = 0;
        int fiveCentsCount = 0;
        int oneCentsCount = 0;
        int reminder = m;

        if (m >= TEN_CENTS) {
            tenCentsCount = m / TEN_CENTS;
            reminder = m % TEN_CENTS;
        }

        if (tenCentsCount > 0 || reminder >= FIVE_CENTS) {
            fiveCentsCount = reminder / FIVE_CENTS;
            reminder = reminder % FIVE_CENTS;
        }

        if (tenCentsCount > 0 || fiveCentsCount > 0 || reminder >= ONE_CENT) {
            oneCentsCount = reminder / ONE_CENT;
        }

        return tenCentsCount + fiveCentsCount + oneCentsCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

