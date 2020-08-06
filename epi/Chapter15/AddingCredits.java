package epi.Chapter15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class AddingCredits {

    public static class ClientsCreditsInfo {
        TreeMap<Integer, Set<String>> max = new TreeMap<>();
        Map<String, Integer> clients = new HashMap<>();
        int offset;

        public void insert(String clientID, int c) {
            remove(clientID);
            max.computeIfAbsent(c, v -> new HashSet<>()).add(clientID);
            clients.put(clientID, c - offset);
        }

        public boolean remove(String clientID) {
            final Integer curr = clients.get(clientID);
            if (curr != null) {
                if (max.containsKey(curr)) {
                    max.get(curr).remove(clientID);
                    if (max.get(curr).isEmpty()) {
                        max.remove(curr);
                    }
                }
            }
            return clients.remove(clientID, curr);
        }

        public int lookup(String clientID) {
            if (!clients.containsKey(clientID)) {
                return -1;
            }
            return offset + clients.get(clientID);
        }

        public void addAll(int credit) {
            offset += credit;
        }

        public String max() {
            return max.lastEntry().getValue().iterator().next();
        }
    }

    @EpiUserType(ctorParams = { String.class, String.class, int.class })
    public static class Operation {
        public String op;
        public String sArg;
        public int iArg;

        public Operation(String op, String sArg, int iArg) {
            this.op = op;
            this.sArg = sArg;
            this.iArg = iArg;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Operation operation = (Operation) o;

            if (iArg != operation.iArg) {
                return false;
            }
            if (!op.equals(operation.op)) {
                return false;
            }
            return sArg.equals(operation.sArg);
        }

        @Override
        public int hashCode() {
            int result = op.hashCode();
            result = 31 * result + sArg.hashCode();
            result = 31 * result + iArg;
            return result;
        }

        @Override
        public String toString() {
            return String.format("%s(%s, %d)", op, sArg, iArg);
        }
    }

    @EpiTest(testDataFile = "adding_credits.tsv")
    public static void ClientsCreditsInfoTester(List<Operation> ops) throws TestFailure {
        final ClientsCreditsInfo cr = new ClientsCreditsInfo();
        int opIdx = 0;
        for (Operation x : ops) {
            final String sArg = x.sArg;
            final int iArg = x.iArg;
            final int result;
            switch (x.op) {
                case "ClientsCreditsInfo":
                    break;
                case "remove":
                    result = cr.remove(sArg) ? 1 : 0;
                    if (result != iArg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result);
                    }
                    break;
                case "insert":
                    cr.insert(sArg, iArg);
                    break;
                case "add_all":
                    cr.addAll(iArg);
                    break;
                case "lookup":
                    result = cr.lookup(sArg);
                    if (result != iArg) {
                        throw new TestFailure()
                                .withProperty(TestFailure.PropertyName.STATE, cr)
                                .withProperty(TestFailure.PropertyName.COMMAND, x)
                                .withMismatchInfo(opIdx, iArg, result);
                    }
            }
            opIdx++;
        }
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private AddingCredits() {}
}
