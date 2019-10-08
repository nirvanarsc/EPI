package epi.Chapter13;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public class LruCache {
    LinkedHashMap<Integer, Integer> map;

    LruCache(final int capacity) {
        map = new LinkedHashMap<Integer, Integer>(capacity, 0.75F, true) {

            private static final long serialVersionUID = 3168251386275155489L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public Integer lookup(Integer key) {
        return map.getOrDefault(key, -1);
    }

    public void insert(Integer key, Integer value) {
        map.putIfAbsent(key, value);
    }

    public Boolean erase(Object key) {
        return map.remove(key) != null;
    }

    @EpiUserType(ctorParams = { String.class, int.class, int.class })
    public static class Op {
        String code;
        int i1;
        int i2;

        public Op(String code, int i1, int i2) {
            this.code = code;
            this.i1 = i1;
            this.i2 = i2;
        }
    }

    @EpiTest(testDataFile = "lru_cache.tsv")
    public static void runTest(List<Op> commands) throws TestFailure {
        if (commands.isEmpty() || !"LruCache".equals(commands.get(0).code)) {
            throw new RuntimeException("Expected LruCache as first command");
        }
        final LruCache cache = new LruCache(commands.get(0).i1);
        for (Op op : commands.subList(1, commands.size())) {
            final int result;
            switch (op.code) {
                case "lookup":
                    result = cache.lookup(op.i1);
                    if (result != op.i2) {
                        throw new TestFailure("Lookup: expected " + op.i2 + ", got " + result);
                    }
                    break;
                case "insert":
                    cache.insert(op.i1, op.i2);
                    break;
                case "erase":
                    result = cache.erase(op.i1) ? 1 : 0;
                    if (result != op.i2) {
                        throw new TestFailure("Erase: expected " + op.i2 + ", got " + result);
                    }
                    break;
                default:
                    throw new RuntimeException("Unexpected command " + op.code);
            }
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }
}
