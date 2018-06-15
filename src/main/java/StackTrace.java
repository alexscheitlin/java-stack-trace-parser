import java.util.ArrayList;
import java.util.List;

public class StackTrace {
    public String firstLine;
    public List<String> stackLines;

    public StackTrace() {
        this.stackLines = new ArrayList<String>();
    }
}
