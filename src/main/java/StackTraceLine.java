public class StackTraceLine {
    public String packageName;
    public String className;
    public String methodName;
    public String fileName;
    public String lineNumber;
    public String information;

    public String getOriginalLine() {
        if (information == null) {
            return "\tat " + packageName + "." + className + "." + methodName + "(" + fileName + ":" + lineNumber + ")";
        } else {
            return "\tat " + packageName + "." + className + "." + methodName + "(" + information + ")";
        }
    }
}
