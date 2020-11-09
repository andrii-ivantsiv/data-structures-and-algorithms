import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(List<Integer> a, int left, int right) {
        int size = a.size();
        if (size == 1) {
            return 1;
        }

        int limit = size / 2;
        List<Integer> sort = mergeSort(a);
        int count = 1;
        for (int i = 1; i < sort.size(); i++) {
            if (sort.get(i).compareTo(sort.get(i - 1)) == 0) {
                ++count;
                if (count > limit) {
                    return 1;
                }
            } else {
                count = 1;
            }
        }

        return -1;
    }

    private static List<Integer> mergeSort(List<Integer> array) {
        int length = array.size();
        if (length <= 1) {
            return array;
        }

        int middle = length / 2;

        return merge(mergeSort(array.subList(0, middle)), mergeSort(array.subList(middle, length)));
    }

    private static List<Integer> merge(List<Integer> listA, List<Integer> listB) {
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iteratorA = listA.iterator();
        Iterator<Integer> iteratorB = listB.iterator();
        Integer nextA = null;
        Integer nextB = null;
        while ((iteratorA.hasNext() || nextA != null) && (iteratorB.hasNext() || nextB != null)) {
            if (nextA == null) {
                nextA = iteratorA.next();
            }
            if (nextB == null) {
                nextB = iteratorB.next();
            }

            if (nextA.compareTo(nextB) > 0) {
                result.add(nextB);
                nextB = null;
            } else if (nextA.compareTo(nextB) < 0) {
                result.add(nextA);
                nextA = null;
            } else {
                result.add(nextA);
                result.add(nextB);
                nextB = null;
                nextA = null;
            }
        }

        if (nextA != null) {
            result.add(nextA);
        }
        if (iteratorA.hasNext()) {
            while (iteratorA.hasNext()) {
                result.add(iteratorA.next());
            }
        }
        if (nextB != null) {
            result.add(nextB);
        }
        if (iteratorB.hasNext()) {
            while (iteratorB.hasNext()) {
                result.add(iteratorB.next());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }
        if (getMajorityElement(a, 0, a.size()) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
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

