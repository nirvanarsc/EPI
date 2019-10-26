package epi.Chapter15;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.TestFailure;
import epi.utils.TestRunner;

public final class AddingCredits {

    static class ClientData implements Comparable<ClientData> {
        String id;
        int credit;

        ClientData(String id, int credit) {
            this.id = id;
            this.credit = credit;
        }

        @Override
        public int compareTo(ClientData o) {
            return Integer.compare(o.credit, credit);
        }

        @Override
        public String toString() {
            return id + ' ' + credit;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ClientData)) { return false; }
            return compareTo((ClientData) o) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, credit);
        }
    }

    public static class ClientsCreditsInfo {
        static int globalCredit;
        static NavigableSet<ClientData> clientData;
        static Map<String, ClientData> ids;

        ClientsCreditsInfo() {
            globalCredit = 0;
            clientData = new TreeSet<>();
            ids = new HashMap<>();
        }

        public void insert(String clientID, int c) {
            remove(clientID);
            final ClientData item = new ClientData(clientID, c - globalCredit);
            ids.put(clientID, item);
            clientData.add(item);
        }

        public boolean remove(String clientID) {
            if (!ids.containsKey(clientID)) {
                return false;
            }
            clientData.remove(ids.remove(clientID));
            return true;
        }

        public int lookup(String clientID) {
            return ids.containsKey(clientID) ? ids.get(clientID).credit + globalCredit : -1;
        }

        public void addAll(int credit) {
            globalCredit += credit;
        }

        public String max() {
            return clientData.first().id;
        }

        @Override
        public String toString() {
            return clientData.toString();
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
