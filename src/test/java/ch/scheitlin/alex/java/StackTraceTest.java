package ch.scheitlin.alex.java;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StackTraceTest {

    @Test
    public void getFirstLine() {
        // assign variables with test data
        String expectedLine = "First Line";

        // allocate test objects
        StackTrace stackTrace = new StackTrace(expectedLine, null);

        // execute methods to be tested
        String actualLine = stackTrace.getFirstLine();

        // assign result
        Assert.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void getStackTraceLines() {
        // assign variables with test data
        List<StackTraceElement> expectedLines = new ArrayList<StackTraceElement>();

        // allocate test objects
        StackTrace stackTrace = new StackTrace(null, expectedLines);

        // execute methods to be tested
        List<StackTraceElement> actualLines = stackTrace.getStackTraceLines();

        // assign result
        Assert.assertEquals(expectedLines, actualLines);
    }

    @Test
    public void getOriginalStackTrace() {
        // assign variables with test data
        String expectedStackTrace = getDummyStackTraceString();

        // allocate test objects
        StackTrace stackTrace = getDummyStackTraceObject();

        // execute methods to be tested
        String actualStackTrace = stackTrace.getOriginalStackTrace();

        // assign result
        Assert.assertEquals(expectedStackTrace, actualStackTrace);
    }

    @Test
    public void getLinesOfPackage() {
        // allocate test objects
        StackTrace stackTrace = getDummyStackTraceObject();

        // assign variables with test data
        List<StackTraceElement> expectedLines = new ArrayList<StackTraceElement>();
        expectedLines.add(stackTrace.getStackTraceLines().get(3));

        // execute methods to be tested
        List<StackTraceElement> actualLines = stackTrace.getLinesOfPackage("com.example.bank");

        // assign result
        Assert.assertEquals(expectedLines.size(), actualLines.size());

        for (int i = 0; i < expectedLines.size(); i ++) {
            Assert.assertEquals(expectedLines.get(i), actualLines.get(i));
        }
    }

    private String getDummyStackTraceString() {
        return "java.lang.AssertionError\n" +
                "\tat org.junit.Assert.fail(Assert.java:86)\n" +
                "\tat org.junit.Assert.assertTrue(Assert.java:41)\n" +
                "\tat org.junit.Assert.assertTrue(Assert.java:52)\n" +
                "\tat com.example.bank.BankAccountTest.getNumber(BankAccountTest.java:21)";
    }

    private StackTrace getDummyStackTraceObject() {

        // assign variables with test data
        String line1 = "java.lang.AssertionError";
        StackTraceElement line2 = new StackTraceElement("org.junit.Assert","fail", "Assert.java", 86);
        StackTraceElement line3 = new StackTraceElement("org.junit.Assert","assertTrue", "Assert.java", 41);
        StackTraceElement line4 = new StackTraceElement("org.junit.Assert","assertTrue", "Assert.java", 52);
        StackTraceElement line5 = new StackTraceElement("com.example.bank.BankAccountTest","getNumber", "BankAccountTest.java", 21);
        List<StackTraceElement> lines = new ArrayList<StackTraceElement>();
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);
        lines.add(line5);

        return new StackTrace(line1, lines);
    }
}
