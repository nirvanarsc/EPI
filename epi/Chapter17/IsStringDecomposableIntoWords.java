package epi.Chapter17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import epi.test_framework.EpiTest;
import epi.test_framework.TimedExecutor;
import epi.utils.TestRunner;
import epi.utils.Verifiers;

public final class IsStringDecomposableIntoWords {

    public static List<String> decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        final List<String> res = new ArrayList<>();
        if (dfs(domain, 0, new StringBuilder(), dictionary, res, new HashMap<>())) {
            return res;
        }
        return Collections.emptyList();
    }

    private static boolean dfs(String word, int idx, StringBuilder sb, Set<String> dict, List<String> res,
                               Map<String, Boolean> dp) {
        if (idx == word.length()) {
            if (dict.contains(sb.toString())) {
                res.add(sb.toString());
                return true;
            }
            return false;
        }
        final String key = idx + "," + sb;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        sb.append(word.charAt(idx));
        if (dfs(word, idx + 1, new StringBuilder(sb), dict, res, dp)) {
            dp.put(key, true);
            return true;
        }
        if (dict.contains(sb.toString())) {
            res.add(sb.toString());
            if (dfs(word, idx + 1, new StringBuilder(), dict, res, dp)) {
                dp.put(key, true);
                return true;
            }
            res.remove(res.size() - 1);
        }
        dp.put(key, false);
        return false;
    }

    @EpiTest(testDataFile = "is_string_decomposable_into_words.tsv")
    public static void decomposeIntoDictionaryWordsWrapper(TimedExecutor executor,
                                                           String domain,
                                                           Set<String> dictionary,
                                                           Boolean decomposable) throws Exception {
        final List<String> result = executor.run(() -> decomposeIntoDictionaryWords(domain, dictionary));
        Verifiers.verifyDecomposableIntoWords(domain, dictionary, decomposable, result);
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private IsStringDecomposableIntoWords() {}
}
