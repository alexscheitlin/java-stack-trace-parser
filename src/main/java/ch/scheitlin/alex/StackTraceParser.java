package ch.scheitlin.alex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackTraceParser {
    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\.([\d\w\$]*)\((?:(?:([\d\w]*\.java):(\d*))|([\d\w\s]*))\)$
    // group 1: package name
    // group 2: class name
    // group 3: method name
    // group 4: file name | null
    // group 5: line number | null
    // group 6: null | string
    private static String STACK_LINE_REGEX = "^\\tat ((?:(?:[\\d\\w]*\\.)*[\\d\\w]*))\\.([\\d\\w\\$]*)\\.([\\d\\w\\$]*)\\((?:(?:([\\d\\w]*\\.java):(\\d*))|([\\d\\w\\s]*))\\)$";
    private static Pattern STACK_LINE_PATTERN = Pattern.compile(STACK_LINE_REGEX);

    public static StackTrace parse(String stackTraceString) {
        String[] lines = stackTraceString.split("\n");

        StackTrace stackTrace = new StackTrace();
        stackTrace.firstLine = lines[0];

        for (int i = 1; i < lines.length; i++) {
            Matcher matcher = STACK_LINE_PATTERN.matcher(lines[i]);

            if (matcher.matches()) {
                String packageName = matcher.group(1);
                String className = matcher.group(2);
                String methodName = matcher.group(3);

                String fileName = null;
                if (matcher.group(4) != null) {
                    fileName = matcher.group(4);
                }

                int lineNumber = -1;
                if (matcher.group(5) != null) {
                    lineNumber = Integer.valueOf(matcher.group(5));
                }

                // pass -2 as line number if the method is a native method
                // see isNativeMethod() of StackTraceElement implementation
                if (matcher.group(6) != null && matcher.group(6).equals("Native Method")) {
                    lineNumber = -2;

                }

                StackTraceElement element = new StackTraceElement(
                        packageName + "." + className,
                        methodName,
                        fileName,
                        lineNumber
                );

                if (!("\tat " + element.toString()).equals(lines[i])) {
                    System.out.println("Error:");
                    System.out.println("Original:\t" + lines[i]);
                    System.out.println("Parsed:\t\t" + element.toString());
                }

                stackTrace.stackLines.add(element);
            }
        }

        return stackTrace;
    }
}
