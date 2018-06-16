package ch.scheitlin.alex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackTrace {
    public String firstLine;
    public List<StackTraceElement> stackTraceLines;

    public StackTrace() {
        this.stackTraceLines = new ArrayList<StackTraceElement>();
    }

    /**
     * Returns the original stack trace.
     *
     * @return the original stack trace
     */
    public String getOriginalStackTrace() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.firstLine).append("\n");
        for (StackTraceElement element : stackTraceLines) {
            builder.append("\tat ").append(element).append("\n");
        }

        return builder.substring(0, builder.length() - 1);
    }

    /**
     * Returns all lines of the stack trace of the specified package.
     *
     * @param packageName the package name to get the stack trace lines from
     * @return a List of StackTraceElements of the specified package
     */
    public List<StackTraceElement> getLinesOfPackage(String packageName) {
        List<StackTraceElement> linesOfPackage = new ArrayList<StackTraceElement>();

        for (StackTraceElement line : this.stackTraceLines) {
            if (line.toString().startsWith(packageName)) {
                linesOfPackage.add(line);
            }
        }

        return linesOfPackage;
    }
}
