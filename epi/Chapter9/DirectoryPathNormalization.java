package epi.Chapter9;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        final Deque<String> stack = new ArrayDeque<>();
        for (String p : path.split("/")) {
            if (p.isEmpty() || ".".equals(p)) { continue; }
            if ("..".equals(p)) {
                if (stack.isEmpty() || stack.getFirst().equals(p)) {
                    stack.addFirst(p);
                } else if (!stack.getFirst().equals(p)) {
                    stack.removeFirst();
                }
            } else {
                stack.addFirst(p);
            }
        }
        final StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append('/' + stack.removeLast());
        }
        if (path.charAt(0) != '/') {
            sb.deleteCharAt(0);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DirectoryPathNormalization() {}
}
