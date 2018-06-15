import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackTraceParser {
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\(([\d\w]*\.java):(\d*)\)$
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*)(?:\.([\d\w\$]*)){1,2})\(([\d\w]*\.java):(\d*)\)$
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\.([\d\w\$]*)\(([\d\w]*\.java):(\d*)\)$
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\.([\d\w\$]*)\((?:(?:([\d\w]*\.java):(\d*))|(?:([\d\w]*) ([\d\w]*)))\)$
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\.([\d\w\$]*)\((?:(?:([\d\w]*\.java):(\d*))|([\d\w\s]*))\)$
    // group 1: package name
    // group 2: class name
    // group 3: method name
    // group 4: file name | null
    // group 5: line number | null
    // group 6: null | string
    private static String STACK_LINE_REGEX =
            "^\\tat ((?:(?:[\\d\\w]*\\.)*[\\d\\w]*))\\.([\\d\\w\\$]*)\\.([\\d\\w\\$]*)\\((?:(?:([\\d\\w]*\\.java):(\\d*))|([\\d\\w\\s]*))\\)$$";
    private static Pattern STACK_LINE_PATTERN = Pattern.compile(STACK_LINE_REGEX);

    public static StackTrace parse(String stackTraceString) {
        String[] lines = stackTraceString.split("\n");

        StackTrace stackTrace = new StackTrace();
        stackTrace.firstLine = lines[0];

        for (int i = 1; i < lines.length; i++) {
            Matcher matcher = STACK_LINE_PATTERN.matcher(lines[i]);
            StackTraceLine stackTraceLine = new StackTraceLine();
            if (matcher.matches()) {
                stackTraceLine.packageName = matcher.group(1);
                stackTraceLine.className = matcher.group(2);
                stackTraceLine.methodName = matcher.group(3);

                if (matcher.group(4) != null && matcher.group(4) != null) {
                    stackTraceLine.fileName = matcher.group(4);
                    stackTraceLine.lineNumber = matcher.group(5);
                } else if (matcher.group(6) != null) {
                    stackTraceLine.information = matcher.group(6);
                }

                if (!stackTraceLine.getOriginalLine().equals(lines[i])) {
                    System.out.println("Error:");
                    System.out.println("Original:\t" + lines[i]);
                    System.out.println("Parsed:\t\t" + stackTraceLine.getOriginalLine());
                }

                stackTrace.stackLines.add(stackTraceLine);
            }
        }

        return stackTrace;
    }
}
