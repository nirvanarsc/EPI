package epi.Chapter9;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

import java.util.Deque;
import java.util.LinkedList;

public final class DirectoryPathNormalization {

    @EpiTest(testDataFile = "directory_path_normalization.tsv")
    public static String shortestEquivalentPath(String path) {
        final Deque<String> normalizedPath = new LinkedList<>();
        final String[] split = path.split("/");

        for (String s : split) {
            if ("..".equals(s)) {
                if (normalizedPath.isEmpty() || normalizedPath.peekLast().equals(s)) {
                    normalizedPath.addLast(s);
                } else {
                    if (normalizedPath.peekLast().equals(s)) {
                        normalizedPath.addLast(s);
                    } else {
                        normalizedPath.pollLast();
                    }
                }
            } else if (!".".equals(s) && !s.isEmpty()) {
                normalizedPath.addLast(s);
            }
        }

        final String normalized = String.join("/", normalizedPath);
        return path.charAt(0) == '/' ? '/' + normalized : normalized;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private DirectoryPathNormalization() {
    }
}
