package ch.scheitlin.alex.java;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class StackTraceParserTest {

    @Test
    public void parse() {
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