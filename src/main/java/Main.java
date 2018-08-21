import ch.scheitlin.alex.java.StackTrace;
import ch.scheitlin.alex.java.StackTraceParser;
import ch.scheitlin.alex.java.ResourceReader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Example usage of the {@code StackTraceParser} class and the {@code StackTrace} object.
 */
public class Main {
    public static void main(String[] args) {
        // read dummy stack trace
        String stackTraceString = null;
        try {
            stackTraceString = ResourceReader.readResourceFile("dummyStackTrace.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Could not read test resource file!");
            return;
        }

        // parse stack trace
        StackTrace stackTrace = null;
        try {
            stackTrace = StackTraceParser.parse(stackTraceString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // print first line of stack trace
        String firstLine = stackTrace.getFirstLine();

        // print detailed information about some stack trace line
        StackTraceElement stackLine = stackTrace.getStackTraceLines().get(0);
        System.out.println();
        System.out.println("First line:\t" + firstLine);
        System.out.println("Stack Line:\t" + stackLine.toString());
        System.out.println("\tDeclaring class:\t" + stackLine.getClassName());
        System.out.println("\tMethod name:\t\t" + stackLine.getMethodName());
        System.out.println("\tFile name:\t\t\t" + stackLine.getFileName());
        System.out.println("\tLine number:\t\t" + stackLine.getLineNumber());
        System.out.println("\tIs Native Method:\t" + stackLine.isNativeMethod());

        // print stack trace lines of a specific package
        String packageName = "com.example.bank";
        List<StackTraceElement> linesOfPackage = stackTrace.getLinesOfPackage(packageName);
        System.out.println();
        for (StackTraceElement line : linesOfPackage) {
            System.out.println(line.toString());
        }

        // print reconstructed original stack trace after parsing
        System.out.println(stackTrace.getOriginalStackTrace());
    }
}
