import java.util.List;
import java.util.Scanner;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class CarFueling {

    static int computeMinRefills(int dist, int tank, int[] stops) {
        if (dist <= tank) {
            return 0;
        }

        List<Integer> gasStations = stream(stops).boxed().collect(toList());
        gasStations.add(0, 0);
        gasStations.add(dist);

        int refillCount = 0;
        int gasStationsSize = gasStations.size();
        for (int i = 0; i < gasStationsSize; i++) {
            int currentGasStation = gasStations.get(i);
            int nextIndex = i + 1;
            for (int j = nextIndex; j < gasStationsSize; j++) {
                int nextGasStation = gasStations.get(j);
                if (j == nextIndex && nextGasStation - currentGasStation > tank) {
                    return -1;
                }
                if (currentGasStation + tank < nextGasStation) {
                    i = j - 2;
                    ++refillCount;
                    break;
                }
            }
        }

        return refillCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
