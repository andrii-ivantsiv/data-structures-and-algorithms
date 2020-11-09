import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.compare;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class CoveringSegments {

    private static Set<Integer> optimalPoints(Set<Segment> segments) {
        List<Integer> allPoints = segments.stream()
                .map(Segment::getAllPoints)
                .flatMap(List::stream)
                .sorted()
                .collect(toList());

        Map<Integer, Integer> pointsRating = new LinkedHashMap<>();
        for (int i = 0; i < allPoints.size() - 1; i++) {
            int current = allPoints.get(i);
            if (current == allPoints.get(i + 1)) {
                int rating = pointsRating.getOrDefault(current, 1) + 1;
                pointsRating.put(current, rating);
                if (rating == segments.size()) {
                    return singleton(current);
                }
            }
        }

        pointsRating = pointsRating.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        List<Segment> processedSegments = new LinkedList<>(segments);
        Set<Integer> points = new TreeSet<>();
        for (Map.Entry<Integer, Integer> entry : pointsRating.entrySet()) {
            for (ListIterator<Segment> iterator = processedSegments.listIterator(); iterator.hasNext(); ) {
                Integer point = entry.getKey();
                Segment segment = iterator.next();
                if (segment.getAllPoints().contains(point)) {
                    points.add(point);
                    iterator.remove();
                }

                if (processedSegments.isEmpty()) {
                    return points;
                }
            }
        }

        return points;
    }

    private static Set<Integer> optimalPointsV2(NavigableSet<Segment> segments) {
        Set<Integer> points = new TreeSet<>();

        TreeSet<Segment> interceptedSegments = new TreeSet<>();

        for (Iterator<Segment> i = segments.iterator(); i.hasNext(); ) {
            Segment segment = i.next();
            i.remove();
            interceptedSegments.add(segment);
            for (Iterator<Segment> j = segments.iterator(); j.hasNext(); ) {
                Segment next = j.next();

                boolean intercepted = true;
                for (Segment interceptedSegment : interceptedSegments) {
                    if (!next.intercepted(interceptedSegment)) {
                        intercepted = false;
                    }
                }

                if (intercepted) {
                    interceptedSegments.add(next);
                    j.remove();
                }
            }
            points.add(interceptedPoint(interceptedSegments));
            interceptedSegments.clear();
            i = segments.iterator();
        }

        return points;
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;
        List<Integer> points;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public List<Integer> getAllPoints() {
            if (points == null) {
                points = start == end
                        ? asList(start, end)
                        : range(start, end + 1).boxed().collect(toList());
            }

            return points;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Segment segment = (Segment) o;
            return start == segment.start &&
                    end == segment.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public int compareTo(Segment o) {
            int resultComparison = compare(start, o.start);
            return resultComparison == 0
                    ? compare(end, o.end)
                    : resultComparison;
        }

        public boolean intercepted(Segment o) {
            // this = (1 3) and o = (2 4)
            if (start < o.start && end >= o.start) {
                return true;
            }

            // this = (2 4) and o = (1 3)
            if (start > o.start && start <= o.end) {
                return true;
            }

            // this = (2 4) and o = (2 3)
            return start == o.start;
        }
    }

    public static int interceptedPoint(TreeSet<Segment> segments) {
        Segment result = segments.pollFirst();

        for (Segment segment : segments) {
            // result = (1 3) segment = (2 4)
            if (result.start < segment.start && result.end >= segment.start) {
                // 2 3
                result = new Segment(segment.start, min(result.end, segment.end));
                continue;
            }
            // result = (2 4) segment = (1 3)
            if (result.start > segment.start && result.start <= segment.end) {
                // 2 3
                result = new Segment(result.start, segment.end);
                continue;
            }
            // result = (1 4) segment = (1 3)
            if (result.start == segment.start) {
                // 1 3
                result = new Segment(result.start, min(result.end, segment.end));
            }
        }

        return result.end;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TreeSet<Segment> segments = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        //Set<Integer> points = optimalPoints(segments);
        Set<Integer> points = optimalPointsV2(segments);
        System.out.println(points.size());
        for (Integer point : points) {
            System.out.print(point + " ");
        }
    }
}
 
