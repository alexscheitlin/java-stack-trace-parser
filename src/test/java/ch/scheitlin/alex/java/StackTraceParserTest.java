package ch.scheitlin.alex.java;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class StackTraceParserTest {

    @Test
    public void parse_lines() {
        // assign variables with test data
        String expectedStackTrace = getDummyStackTrace();

        // execute methods to be tested
        List<String> stackTraceLines = Arrays.asList(expectedStackTrace.split("\n"));
        String actualStackTrace;
        try {
            actualStackTrace = StackTraceParser.parse(stackTraceLines).getOriginalStackTrace();
        } catch (Exception e) {
            fail("Could not parse the stack trace!");
            return;
        }

        // assign result
        Assert.assertEquals(expectedStackTrace, actualStackTrace);
    }

    @Test
    public void parse_string() {
        // assign variables with test data
        String expectedStackTrace = getDummyStackTrace();

        // execute methods to be tested
        String actualStackTrace;
        try {
            actualStackTrace = StackTraceParser.parse(expectedStackTrace).getOriginalStackTrace();
        } catch (Exception e) {
            fail("Could not parse the stack trace!");
            return;
        }

        // assign result
        Assert.assertEquals(expectedStackTrace, actualStackTrace);
    }

    private String getDummyStackTrace() {
        try {
            return TestResourceReader.readResourceFile("dummyStackTrace.txt");
        } catch (FileNotFoundException e) {
            fail("Could not read test resource file!");
            return null;
        }
    }
}