package ch.scheitlin.alex.java;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.fail;

public class StackTraceParserTest {

    @Test
    public void parse() {
        // arrange
        String originalStackTrace = null;
        try {
            originalStackTrace = TestResourceReader.readResourceFile("dummyStackTrace.txt");
        } catch (FileNotFoundException e) {
            fail("Could not read test resource file!");
        }

        // act
        try {
            StackTraceParser.parse(originalStackTrace);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}