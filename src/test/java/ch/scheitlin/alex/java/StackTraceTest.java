package ch.scheitlin.alex.java;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.fail;

public class StackTraceTest {

    @Test
    public void getOriginalStackTrace() {
        // arrange
        String originalStackTrace = null;
        try {
            originalStackTrace = TestResourceReader.readResourceFile("dummyStackTrace.txt");
        } catch (FileNotFoundException e) {
            fail("Could not read test resource file!");
        }


        StackTrace stackTrace = null;
        try {
            stackTrace = StackTraceParser.parse(originalStackTrace);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // act
        String retrievedStackTrace = stackTrace.getOriginalStackTrace();

        // assert
        Assert.assertEquals(originalStackTrace, retrievedStackTrace);
    }
}
