package epi.Chapter7;

import epi.test_framework.EpiTest;
import epi.utils.TestRunner;

public final class SpreadsheetEncoding {

    @EpiTest(testDataFile = "spreadsheet_encoding.tsv")
    public static int ssDecodeColID(String col) {
        int res = 0;
        for (char c : col.toCharArray()) {
            res = res * 26 + c - 'A' + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        TestRunner.run(args);
    }

    private SpreadsheetEncoding() {}
}
