package ch.scheitlin.alex.java;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTraceTest {

    @Test
    public void getOriginalStackTrace() {
        // arrange
        String originalStackTrace = DummyStackTrace.getDummyStackTrace();
        StackTrace stackTrace = null;
        try {
            stackTrace = StackTraceParser.parse(originalStackTrace);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // act
        String retrievedStackTrace = stackTrace.getOriginalStackTrace();

        // assert
        assertEquals(originalStackTrace, retrievedStackTrace);
    }
}
