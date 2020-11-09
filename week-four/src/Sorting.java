import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int m = l + random.nextInt(r - l), m1 = m, m2 = m;
        int x = a[m];

        int i = l, j = r;
        while (i < m1 || j > m2) {
            if (i < m1) {
                if (a[i] == x) {
                    m1--;
                    swap(a, i, m1);
                }
                if (a[i] > x) {
                    swap(a, m2, i);
                    m1--;
                    m2--;
                    swap(a, i, m1);

                }
                if (a[i] < x) {
                    i++;
                }
            }
            if (j > m2) {
                if (a[j] == x) {
                    m2++;
                    swap(a, j, m2);
                }
                if (a[j] < x) {
                    swap(a, j, m1);
                    m1++;
                    m2++;
                    swap(a, j, m2);
                }
                if (a[j] > x) {
                    j--;
                }
            }
        }

        return new int[]{m1, m2};
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        //System.out.println("k=" + k);
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        //int m = partition2(a, l, r);
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0] - 1);
        randomizedQuickSort(a, m[1] + 1, r);
    }

    public static void main(String[] args) {
        /*boolean c = true;
        while (c) {
            int l = random.nextInt(100);
            int[] a = new int[l];
            int[] b = new int[l];
            for (int i = 0; i < l; i++) {
                int e = random.nextInt(100);
                a[i] = e;
                b[i] = e;
            }

            System.out.println("Array: " + Arrays.toString(a));
            randomizedQuickSort(a, 0, l - 1);
            Arrays.sort(b);
            System.out.println("RandomizedQuickSort: " + Arrays.toString(a));
            System.out.println("Array.sort:          " + Arrays.toString(b));

            for (int i = 0; i < l; i++) {
                if (a[i] != b[i]) {
                    System.out.println("FAIL!!!");
                    System.out.println("index:               " + i);
                    System.out.println("RandomizedQuickSort: " + Arrays.toString(a));
                    System.out.println("Array.sort:          " + Arrays.toString(b));
                    c = false;
                    break;
                }
            }
        }*/

        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        //quickSort(a, 0, n - 1);
        //Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
            return;

        if (low >= high)
            return;

        // pick the pivot
        //int middle = low + (high - low) / 2;
        int middle = random.nextInt(high - low + 1) + low;
        int t = arr[low];
        arr[low] = arr[middle];
        arr[middle] = t;

        int pivot = arr[high];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j)
            quickSort(arr, low, j);

        if (high > i)
            quickSort(arr, i, high);
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

