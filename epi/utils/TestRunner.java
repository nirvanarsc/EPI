package epi.utils;

import epi.test_framework.GenericTest;

public final class TestRunner {

    public static void run(String[] args) {
        final String className = System.getProperty("sun.java.command");
        try {
            System.exit(GenericTest.runFromAnnotations(args, className, Class.forName(className)).ordinal());
        } catch (ClassNotFoundException e) {
            System.err.println("Class " + className + " not found.");
        }
    }

    private TestRunner() {
    }
}