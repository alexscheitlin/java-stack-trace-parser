package ch.scheitlin.alex;

import org.junit.Test;

public class StackTraceParserTest {

    @Test
    public void parse() {
        // arrange
        String originalStackTrace = DummyStackTrace.getDummyStackTrace();

        // act
        try {
            StackTraceParser.parse(originalStackTrace);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}