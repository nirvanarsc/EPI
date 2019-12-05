package epi.Chapter18;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class MajorityElement {

    public static String majoritySearch(Iterator<String> stream) {
        String res = stream.next();
        int times = 1;
        while (stream.hasNext()) {
            final String next = stream.next();
            if (times == 0) {
                res = next;
                times = 1;
            } else if (res.equals(next)) {
                times++;
            } else {
                times--;
            }
        }

        return res;
    }

    public static String majoritySearch2(Iterator<String> stream) {
        final Map<String, Integer> map = new HashMap<>();
        String res = "";
        int max = 0;
        while (stream.hasNext()) {
            final String next = stream.next();
            final int merge = map.merge(next, 1, Integer::sum);
            if (merge > max) {
                max = merge;
                res = next;
            }
        }

        return res;
    }

    @EpiTest(testDataFile = "majority_element.tsv")
    public static String majoritySearchWrapper(List<String> stream) {
        return majoritySearch(stream.iterator());
    }

    @EpiTest(testDataFile = "majority_element.tsv")
    public static String majoritySearchWrapper2(List<String> stream) {
        return majoritySearch2(stream.iterator());
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private MajorityElement() {}
}
