package ch.scheitlin.alex;

import java.util.ArrayList;
import java.util.List;

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
}
