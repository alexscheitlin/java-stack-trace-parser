package ch.scheitlin.alex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackTraceParser {
    // A typical stack trace element looks like follows:
    // com.myPackage.myClass.myMethod(myClass.java:1)
    // component        example             allowed signs
    // package name:    com.myPackage       alphabetical / numbers
    // class name:      myClass             alphabetical / numbers / $-sign for anonymous inner classes
    // method name:     myMethod            alphabetical / numbers / $-sign for lambda expressions
    // file name:       myClass.java        alphabetical / numbers
    // line number:     1                   integer

    // The following lines show some example stack trace elements:
    // org.junit.Assert.fail(Assert.java:86)                                             // typical stack trace element
    // sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)                       // native method
    // org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)                   // anonymous inner classes
    // org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)                   // lambda expressions
    // org.apache.maven.surefire.junit4.JUnit4TestSet.execute(JUnit4TestSet.java:53)     // numbers for package and class names

    // Using the predefined structure of a stack trace element and allowed signs for its components, the following
    // regular expression can be used to parse stack trace elements and it's components. Parentheses ('(', ')') are used
    // to extract the components and '?:' is used to group the signs but not creating capture groups. Additionally, the
    // typical stack trace output has a leading tab and 'at ' before the stack trace element.

    // ^\tat ((?:(?:[\d\w]*\.)*[\d\w]*))\.([\d\w\$]*)\.([\d\w\$]*)\((?:(?:([\d\w]*\.java):(\d*))|([\d\w\s]*))\)$
    // group 1: package name
    // group 2: class name
    // group 3: method name
    // group 4: file name | null
    // group 5: line number | null
    // group 6: null | string
    private static String STACK_TRACE_LINE_REGEX = "^\\tat ((?:(?:[\\d\\w]*\\.)*[\\d\\w]*))\\.([\\d\\w\\$]*)\\.([\\d\\w\\$]*)\\((?:(?:([\\d\\w]*\\.java):(\\d*))|([\\d\\w\\s]*))\\)$";
    private static Pattern STACK_TRACE_LINE_PATTERN = Pattern.compile(STACK_TRACE_LINE_REGEX);

    /**
     * Reads a stack trace String line by line and converts them into java.lang.StackTraceElement.
     *
     * @param stackTraceString the stack trace
     * @return a StackTrace containing the first (error) line and a list of StackTraceElements
     * @throws Exception if a stack trace line could not be parsed to a java.lang.StackTraceElement
     */
    public static StackTrace parse(String stackTraceString) throws Exception {
        String[] lines = stackTraceString.split("\n");

        StackTrace stackTrace = new StackTrace();
        stackTrace.firstLine = lines[0];

        for (int i = 1; i < lines.length; i++) {
            Matcher matcher = STACK_TRACE_LINE_PATTERN.matcher(lines[i]);

            if (matcher.matches()) {
                String packageName = matcher.group(1);
                String className = matcher.group(2);
                String methodName = matcher.group(3);

                // pass null if no file information is available
                String fileName = null;
                if (matcher.group(4) != null) {
                    fileName = matcher.group(4);
                }

                // pass -1 if no line number information is available
                int lineNumber = -1;
                if (matcher.group(5) != null) {
                    lineNumber = Integer.valueOf(matcher.group(5));
                }

                // pass -2 as if the method containing the execution point is a native method
                if (matcher.group(6) != null && matcher.group(6).equals("Native Method")) {
                    lineNumber = -2;
                }

                StackTraceElement element = new StackTraceElement(
                        packageName + "." + className,
                        methodName,
                        fileName,
                        lineNumber
                );

                // check whether the parsed stack trace element corresponds to the original one
                if (!("\tat " + element.toString()).equals(lines[i])) {
                    throw new Exception("ERROR: Stack strace line could not be parsed to StackTraceElement:\n" +
                            "\tOriginal stack trace line:\t" + lines[i] + "\n" +
                            "\tParsed StackTraceElement:\t" + "\tat " + element.toString());
                }

                stackTrace.stackTraceLines.add(element);
            }
        }

        return stackTrace;
    }
}
