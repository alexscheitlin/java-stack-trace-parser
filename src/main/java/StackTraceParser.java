public class StackTraceParser {
    public static StackTrace parse(String stackTraceString) {
        String[] lines = stackTraceString.split("\n");

        StackTrace stackTrace = new StackTrace();
        stackTrace.firstLine = lines[0];

        for (int i = 1; i < lines.length; i++) {
            stackTrace.stackLines.add(lines[i]);
        }

        return stackTrace;
    }
}
