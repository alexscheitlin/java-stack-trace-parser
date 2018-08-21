# Java Stack Trace Parser

> _Parses a java stack trace to access package, class, method, file, and line information of every stack trace element._


[![Build Status](https://travis-ci.org/alexscheitlin/java-stack-trace-parser.svg?branch=master)](https://travis-ci.org/alexscheitlin/java-stack-trace-parser)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)


## Code Example

The `StackTraceParser` class parses a java stack trace (e.g. below) and maps it to a `StackTrace` object with `java.lang.StackTraceElement`s.

_The example below can be found [here](src/main/java/Main.java) and the dummy stack trace [here](src/main/resources/dummyStackTrace.txt)._

```
java.lang.AssertionError
	at org.junit.Assert.fail(Assert.java:86)
	at org.junit.Assert.assertTrue(Assert.java:41)
	at org.junit.Assert.assertTrue(Assert.java:52)
	at com.example.bank.BankAccountTest.getNumber(BankAccountTest.java:21)
	...
```

### Parse Stack Trace

First read some dummy stack trace:

```java
String stackTraceString = null;
try {
    stackTraceString = ResourceReader.readResourceFile("dummyStackTrace.txt");
} catch (FileNotFoundException e) {
    System.out.println("Could not read test resource file!");
    return;
}
```

Then parse the string to map it to a `StackTrace` object.

```java
StackTrace stackTrace = null;
try {
    stackTrace = StackTraceParser.parse(stackTraceString);
} catch (Exception e) {
    System.out.println("Could not read test resource file!");
}
```

### Access Error

To access the first stack trace line (the line indicating the caused error) use the following command:

```java
String firstLine = stackTrace.getFirstLine();
```

### Access Stack Trace Elements

To get detailed information about some stack trace line use the following command:

```java
StackTraceElement stackLine = stackTrace.getStackTraceLines().get(0);

System.out.println("First line:\t" + firstLine);
System.out.println("Stack Line:\t" + stackLine.toString());
System.out.println("\tDeclaring class:\t" + stackLine.getClassName());
System.out.println("\tMethod name:\t\t" + stackLine.getMethodName());
System.out.println("\tFile name:\t\t\t" + stackLine.getFileName());
System.out.println("\tLine number:\t\t" + stackLine.getLineNumber());
System.out.println("\tIs Native Method:\t" + stackLine.isNativeMethod());
```

Expected output:

```
Stack Line:	org.junit.Assert.fail(Assert.java:86)
	Declaring class:	org.junit.Assert
	Method name:		fail
	File name:		Assert.java
	Line number:		86
	Is Native Method:	false
```
_Original stack trace line: `at org.junit.Assert.fail(Assert.java:86)`_

### Get Stack Trace Lines of Package
        
To get stack trace lines of a specific package use the following command:

```java
String packageName = "com.example.bank";
List<StackTraceElement> linesOfPackage = stackTrace.getLinesOfPackage(packageName);

for (StackTraceElement line : linesOfPackage) {
    System.out.println(line.toString());
}
```

Expected output:

```
com.example.bank.BankAccountTest.getNumber(BankAccountTest.java:21)
```

### Reconstruct Stack Trace

To reconstruct the original stack trace use the following command:

```java
System.out.println(stackTrace.getOriginalStackTrace());
```

## Authors

<!--TODO: List all authors -->

- **Author** - *Initial work* - [alexscheitlin](https://github.com/alexscheitlin)

## License

This project is licensed under the [MIT License](LICENSE).
